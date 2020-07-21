package com.revature.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.app.beans.Game;
import com.revature.app.utils.ConnectionUtil;

public class GameOracle implements GameDAO {

	ConnectionUtil cu = ConnectionUtil.getConnectionUtil(); // Our singleton class to create db connection
	public static Logger log = Logger.getLogger(GameOracle.class);
	
	@Override
	public Integer add(Game t) {
		// TODO Auto-generated method stub
		Integer key = 0;
		log.trace("Adding game " + t.getName() + " into the DB");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into game(name, dateReleased, esrbRating, playerLimit) values (?,?,?,?)";
			String[] keys = { "id" };
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName());
			pstmt.setTimestamp(2, new Timestamp(t.getDateReleased().toInstant(ZoneOffset.UTC).toEpochMilli()));
			pstmt.setString(3, t.getEsrbRating());
			pstmt.setString(4, t.getPlayerLimit());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				log.trace("Successfully added game " + t.getName() + " into DB");
				key = rs.getInt(1);
				conn.commit();
			}else {
				log.trace("Failed to add game " + t.getName() + " into DB");
				conn.rollback();
			}
			
		}catch(SQLException e) {
			log.warn("Error has occured while adding game into the DB");
			log.warn(e);
		}
		
		return key;
	}

	@Override
	public Game getById(Integer id) {
		// TODO Auto-generated method stub
		log.trace("Retrieving game with ID: " + id + " from DB");
		Game g = new Game();
		try(Connection conn = cu.getConnection()){
			String sql = "select * from game where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				log.trace("Successfully retrieved game with id: " + id);
				g.setId(rs.getInt("id"));
				g.setName(rs.getString("name"));
				g.setDateReleased(rs.getTimestamp("dateReleased").toLocalDateTime());
				g.setEsrbRating(rs.getString("esrbRating"));
				g.setPlayerLimit(rs.getString("playerLimit"));
			}else {
				log.trace("Cannot find game with ID: " + id);
				return null;
			}
			
		}catch(SQLException e) {
			log.warn("Error has occured while retrieving game from the DB");
			log.warn(e);
		}
		
		return g;
	}

	@Override
	public List<Game> getAll() {
		// TODO Auto-generated method stub
		log.trace("Retrieving all game from the game table");
		List<Game> games = new LinkedList<Game>();
		try(Connection conn = cu.getConnection()){
			String sql = "select * from game";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Game g = new Game();
				g.setId(rs.getInt("id"));
				g.setName(rs.getString("name"));
				g.setDateReleased(rs.getTimestamp("dateReleased").toLocalDateTime());
				g.setEsrbRating(rs.getString("esrbRating"));
				g.setPlayerLimit(rs.getString("playerLimit"));
				games.add(g);
				
			}
			
		}catch(SQLException e) {
			log.warn("Error has occured while retrieving games from DB");
			log.warn(e);
		}
		
		if(!games.isEmpty()) {
			return games;
		}
		return null;
	}

	@Override
	public Boolean update(Game t) {
		// TODO Auto-generated method stub
		log.trace("Updating game " + t.getName() + " from DB");
		try (Connection conn = cu.getConnection()){
			String sql = "update game set name = ?, dateReleased = ?, esrbRating = ?, playerLimit = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getName());
			pstmt.setTimestamp(2, new Timestamp(t.getDateReleased().toInstant(ZoneOffset.UTC).toEpochMilli()));
			pstmt.setString(3, t.getEsrbRating());
			pstmt.setString(4, t.getPlayerLimit());
			pstmt.setInt(5, t.getId());
			int rs = pstmt.executeUpdate();
			if(rs > 0) {
				log.trace("Game (" + t.getName() + ") has been updated!");
			}else {
				log.trace("Failed to update game from DB");
				return false;
			}
		}catch(SQLException e) {
			log.warn("Error has occured while updating game from DB");
			log.warn(e);
			return false;
		}
		
		return true;
	}

	@Override
	public Boolean delete(Game t) {
		// TODO Auto-generated method stub
		log.trace("Deleting game " + t.getName() + " from DB");
		try(Connection conn = cu.getConnection()){
			String sql = "delete from game where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			int rs = pstmt.executeUpdate();
			if(rs>0) {
				log.trace("Successfully deleted game from DB");
			}else {
				log.trace("Cannot find game with id");
				return false;
			}
			
		}catch(SQLException e) {
			log.warn("Error has occured while deleting game from DB");
			log.warn(e);
			return false;
		}
		
		return true;
	}

}
