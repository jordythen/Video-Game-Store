package com.revature.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.app.beans.Publisher;
import com.revature.app.utils.ConnectionUtil;

public class PublisherOracle implements PublisherDAO {

	ConnectionUtil cu = ConnectionUtil.getConnectionUtil(); // Our singleton class to create db connection
	public static Logger log = Logger.getLogger(PublisherOracle.class);

	@Override
	public Integer add(Publisher t) {
		// TODO Auto-generated method stub
		Integer key = 0;
		log.trace("Adding publisher [" + t.getName() + "] into the DB");
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into publisher(name) " + "values (?)";
			String[] keys = { "id" };
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName());
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				log.trace("Successfully added publisher " + t.getName() + " into the DB.");
				key = rs.getInt(1);
				conn.commit();
			} else {
				log.trace("Failed to add publisher " + t.getName() + " into the DB");
				conn.rollback();
			}

		} catch (SQLException e) {
			log.warn("Error has occured while adding publisher into the DB");
			log.warn(e);
		}

		return key;
	}

	public Boolean addPublisherToGame(Integer publisherID, Integer gameID) {

		log.trace("Adding to PUBLISHER_GAME many to many table");
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into PUBLISHER_GAME(publisherID, gameID) values (?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, publisherID);
			pstmt.setInt(2, gameID);
			Integer success = pstmt.executeUpdate();

			if (success > 0) {
				conn.commit();
			} else {
				conn.rollback();
				return false;
			}

		} catch (SQLException e) {
			log.warn("Error has occured while adding data for publisher_game into the DB");
			log.warn(e);
			return false;
		}
		return true;
	}
	
	public List<Publisher> getAllPublishersForGameID(Integer gameID){
		log.trace("Retrieving all publishers that is related to game with ID " + gameID);
		List<Publisher> publishers = new LinkedList<Publisher>();
		try(Connection conn = cu.getConnection()){
			String sql = "select * from publisher_game where gameID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gameID);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Publisher p = new Publisher();
				p = getById(rs.getInt("publisherID"));
				publishers.add(p);
			}
		}catch(SQLException e) {
			log.warn("Error has occured while retrieving publishers from DB");
			log.warn(e);
		}

		if(!publishers.isEmpty()) {
			return publishers;
		}
		return null;
	}

	@Override
	public Publisher getById(Integer id) {
		// TODO Auto-generated method stub
		log.trace("Retrieving publisher with ID: " + id + " from DB");
		Publisher u = new Publisher();
		try (Connection conn = cu.getConnection()) {

			String sql = "select * from publisher where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				log.trace("Successfully retrieved publisher with id: " + id);
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
			} else {
				log.trace("Cannot find publisher with ID: " + id);
				return null;
			}

		} catch (SQLException e) {
			log.warn("Error has occured while retrieving publisher from DB");
			log.warn(e);
		}

		return u;
	}

	@Override
	public List<Publisher> getAll() {
		// TODO Auto-generated method stub
		log.trace("Retrieving all publisher from the DB");
		List<Publisher> publishers = new LinkedList<Publisher>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from publisher";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Publisher c = new Publisher();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));

				publishers.add(c);
			}

		} catch (SQLException e) {
			log.warn("Error has occured while retrieving publishers from DB");
			log.warn(e);
		}

		if (!publishers.isEmpty()) {
			return publishers;
		}
		return null;
	}

	@Override
	public Boolean update(Publisher t) {
		// TODO Auto-generated method stub
		log.trace("Updating publisher " + t.getName() + " from DB");
		try (Connection conn = cu.getConnection()) {
			String sql = "update publisher set name = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getName());
			pstmt.setInt(2, t.getId());
			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				log.trace("Publisher (" + t.getName() + ") has been updated!");

			} else {
				log.trace("Failed to update publisher from DB");
				return false;
			}

		} catch (SQLException e) {
			log.warn("Error has occured while updating publisher from DB");
			log.warn(e);
			return false;
		}

		return true;
	}

	@Override
	public Boolean delete(Publisher t) {
		// TODO Auto-generated method stub
		log.trace("Deleting publisher [" + t.getName() + "] from DB");
		try (Connection conn = cu.getConnection()) {
			String sql = "delete from publisher where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				log.trace("Publisher: " + t.getName() + " has been deleted from DB");
			} else {
				log.trace("Cannot find publisher with such id");
				return false;
			}

		} catch (SQLException e) {
			log.warn("Error has occured while deleting publisher from DB");
			log.warn(e);
			return false;
		}

		return true;
	}

}
