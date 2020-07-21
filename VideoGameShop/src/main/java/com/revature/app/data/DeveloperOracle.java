package com.revature.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.app.beans.Developer;
import com.revature.app.utils.ConnectionUtil;

public class DeveloperOracle implements DeveloperDAO {

	ConnectionUtil cu = ConnectionUtil.getConnectionUtil(); // Our singleton class to create db connection
	public static Logger log = Logger.getLogger(DeveloperOracle.class);

	@Override
	public Integer add(Developer t) {
		// TODO Auto-generated method stub
		Integer key = 0;
		log.trace("Adding category [" + t.getName() + "] into the DB");
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into developer(name, description) " + "values (?,?)";
			String[] keys = { "id" };
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName());
			pstmt.setString(2, t.getDescription());
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				log.trace("Successfully added developer " + t.getName() + " into the DB.");
				key = rs.getInt(1);
				conn.commit();
			} else {
				log.trace("Failed to add developer " + t.getName() + " into the DB");
				conn.rollback();
			}

		} catch (SQLException e) {
			log.warn("Error has occured while adding developer into the DB");
			log.warn(e);
		}

		return key;
	}
	
	public Boolean addPersonToDev(Integer personID, Integer devID) {
		
		log.trace("Adding to DEVELOPER_PERSON many to many table");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into DEVELOPER_PERSON(personID, devID) values (?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, personID);
			pstmt.setInt(2, devID);
			Integer success = pstmt.executeUpdate();
			
			if(success > 0) {
				conn.commit();
			}else {
				conn.rollback();
				return false;
			}
			
		}catch(SQLException e) {
			log.warn("Error has occured while adding developer_person into the DB");
			log.warn(e);
			return false;
		}
		return true;
	}

	@Override
	public Developer getById(Integer id) {
		// TODO Auto-generated method stub
		log.trace("Retrieving category with ID: " + id + " from DB");
		Developer u = new Developer();
		try (Connection conn = cu.getConnection()) {

			String sql = "select * from developer where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				log.trace("Successfully retrieved developer with id: " + id);
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setDescription(rs.getString("description"));
			} else {
				log.trace("Cannot find developer with ID: " + id);
				return null;
			}

		} catch (SQLException e) {
			log.warn("Error has occured while retrieving developer from DB");
			log.warn(e);
		}

		return u;
	}

	@Override
	public List<Developer> getAll() {
		// TODO Auto-generated method stub
		log.trace("Retrieving all devs from the person table");
		List<Developer> developers = new LinkedList<Developer>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from developer";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Developer c = new Developer();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setDescription(rs.getString("description"));

				developers.add(c);
			}

		} catch (SQLException e) {
			log.warn("Error has occured while retrieving developers from DB");
			log.warn(e);
		}

		if (!developers.isEmpty()) {
			return developers;
		}
		return null;
	}

	@Override
	public Boolean update(Developer t) {
		// TODO Auto-generated method stub
		log.trace("Updating developer " + t.getName() + " from DB");
		try (Connection conn = cu.getConnection()) {
			String sql = "update developer set name = ?, description = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getName());
			pstmt.setString(2, t.getDescription());
			pstmt.setInt(3, t.getId());
			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				log.trace("Dev (" + t.getName() + ") has been updated!");

			} else {
				log.trace("Failed to update developer from DB");
				return false;
			}

		} catch (SQLException e) {
			log.warn("Error has occured while updating developer from DB");
			log.warn(e);
			return false;
		}

		return true;
	}

	@Override
	public Boolean delete(Developer t) {
		// TODO Auto-generated method stub
		log.trace("Deleting dev [" + t.getName() + "] from DB");
		try (Connection conn = cu.getConnection()) {
			String sql = "delete from developer where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				log.trace("Developer: " + t.getName() + " has been deleted from DB");
			} else {
				log.trace("Cannot find developer with such id");
				return false;
			}

		} catch (SQLException e) {
			log.warn("Error has occured while deleting developer from DB");
			log.warn(e);
			return false;
		}

		return true;
	}

}
