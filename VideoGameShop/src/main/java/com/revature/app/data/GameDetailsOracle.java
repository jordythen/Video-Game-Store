package com.revature.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.app.beans.GameDetails;
import com.revature.app.utils.ConnectionUtil;

public class GameDetailsOracle implements GameDetailsDAO{

	ConnectionUtil cu = ConnectionUtil.getConnectionUtil(); // Our singleton class to create db connection
	public static Logger log = Logger.getLogger(GameDetailsOracle.class);
	private static GameOracle gDao;
	
	@Autowired
	public GameDetailsOracle() {
		gDao = new GameOracle();
	}
	
	
	@Override
	public Integer add(GameDetails t) {
		// TODO Auto-generated method stub
		Integer key = 0;
		log.trace("Adding gamedetails for game [" + t.getGame().getName() + "] into the DB");
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into gamedetails(gameID, status, quantity, price) " + "values (?,?,?,?)";
			String[] keys = { "id" };
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, t.getGame().getId());
			pstmt.setString(2, t.getStatus());
			pstmt.setInt(3, t.getQuantity());
			pstmt.setDouble(4, t.getPrice());
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				log.trace("Successfully added gamedetails for game " + t.getGame().getName() + " into the DB.");
				key = rs.getInt(1);
				conn.commit();
			} else {
				log.trace("Failed to add gamedetails for game " + t.getGame().getName() + " into the DB");
				conn.rollback();
			}

		} catch (SQLException e) {
			log.warn("Error has occured while adding gamedetails into the DB");
			log.warn(e);
		}

		return key;
	}

	@Override
	public GameDetails getById(Integer id) {
		// TODO Auto-generated method stub
		log.trace("Retrieving gamedetails with ID " + id + " from DB");
		GameDetails gd = new GameDetails();
		try(Connection conn = cu.getConnection()){
			String sql = "select * from gamedetails where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				log.trace("Successfully retrieved gamedetails with id: " + id);
				gd.setId(id);
				gd.setStatus(rs.getString("status"));
				gd.setQuantity(rs.getInt("quantity"));
				gd.setPrice(rs.getDouble("price"));
				gd.setGame(gDao.getById(rs.getInt("gameID")));
				
			}else {
				log.trace("Cannot find gamedetails with ID: " + id);
				return null;
			}
			
		}catch(SQLException e) {
			log.warn("Error has occured while retrieving gamedetails from DB");
			log.warn(e);
		}
		
		return gd;
	}

	@Override
	public List<GameDetails> getAll() {
		// TODO Auto-generated method stub
		log.trace("Retrieving all gamedetails from DB");
		List<GameDetails> gds = new LinkedList<GameDetails>();
		try(Connection conn = cu.getConnection()){
			String sql = "select * from gamedetails";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				GameDetails gd = new GameDetails();
				gd.setId(rs.getInt("id"));
				gd.setStatus(rs.getString("status"));
				gd.setQuantity(rs.getInt("quantity"));
				gd.setPrice(rs.getDouble("price"));
				gd.setGame(gDao.getById(rs.getInt("gameID")));
				gds.add(gd);
			}
		}catch(SQLException e) {
			log.warn("Error has occured while retrieving gamedetails from DB");
			log.warn(e);
		}
		
		if(!gds.isEmpty()) {
			return gds;
		}
		
		return null;
	}

	@Override
	public Boolean update(GameDetails t) {
		// TODO Auto-generated method stub
		log.trace("Updating game details");
		return null;
	}

	@Override
	public Boolean delete(GameDetails t) {
		// TODO Auto-generated method stub
		log.trace("Deleting gamedetails for game " + t.getGame().getName() + " from DB");
		try(Connection conn = cu.getConnection()){
			String sql = "delete from game where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			int rs = pstmt.executeUpdate();
			if (rs>0) {
				log.trace("Successfully deleted gamedetails from DB");
				
			}else {
				log.trace("Cannot find gamedetails with id " + t.getId());
				return false;
			}
			
		}catch(SQLException e) {
			log.warn("Error has occured while deleting gamedetails from DB");
			log.warn(e);
			return false;
		}
		
		return true;
	}

}
