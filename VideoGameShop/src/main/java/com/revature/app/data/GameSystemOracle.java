package com.revature.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.revature.app.beans.GameSystem;
import com.revature.app.beans.GameSystem;
import com.revature.app.utils.ConnectionUtil;

import org.apache.log4j.Logger;

public class GameSystemOracle implements GameSystemDAO {
	ConnectionUtil cu = ConnectionUtil.getConnectionUtil(); // Our singleton class to create db connection
	public static Logger log = Logger.getLogger(GameSystemOracle.class);

	@Override
	public Integer add(GameSystem g) {
		Integer key = 0;
		log.trace("Adding Game System [" + g.getConsoleName() + "] into the DB");
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into GAMESYSTEM(name) " + "values (?)";
			String[] keys = { "id" };
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, g.getConsoleName());
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				log.trace("Successfully added game system " + g.getConsoleName() + " into the DB.");
				key = rs.getInt(1);
				conn.commit();
			} else {
				log.trace("Failed to add game system " + g.getConsoleName() + " into the DB");
				conn.rollback();
			}

		} catch (SQLException e) {
			log.warn("Error has occured while adding game system into the DB");
			log.warn(e);
		}

		return key;
	}

	@Override
	public GameSystem getById(Integer id) {
		// TODO Auto-generated method stub
		log.trace("Retrieving game system with ID: " + id + " from DB");
		GameSystem g = new GameSystem();
		try (Connection conn = cu.getConnection()) {

			String sql = "select * from GAMESYSTEM where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				log.trace("Successfully retrieved game system with id: " + id);
				g.setId(rs.getInt("id"));
				g.setConsoleName(rs.getString("name"));
			} else {
				log.trace("Cannot find game system with ID: " + id);
				return null;
			}

		} catch (SQLException e) {
			log.warn("Error has occured while retrieving game system from DB");
			log.warn(e);
		}

		return g;
	}

	@Override
	public List<GameSystem> getAll() {
		// TODO Auto-generated method stub
		log.trace("Retrieving all game systems from the GAMESYSTEM table");
		List<GameSystem> GameSystems = new LinkedList<GameSystem>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from GAMESYSTEM";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				GameSystem g = new GameSystem();
				g.setId(rs.getInt("id"));
				g.setConsoleName(rs.getString("name"));

				GameSystems.add(g);
			}

		} catch (SQLException e) {
			log.warn("Error has occured while retrieving GameSystems from DB");
			log.warn(e);
		}

		if (!GameSystems.isEmpty()) {
			return GameSystems;
		}
		return null;
	}

	@Override
	public Boolean update(GameSystem g) {
		log.trace("Updating GameSystem " + g.getConsoleName() + " from DB");
		try (Connection conn = cu.getConnection()) {
			String sql = "update GameSystem set name = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getConsoleName());
			pstmt.setInt(2, g.getId());
			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				log.trace("Game System (" + g.getConsoleName() + ") has been updated!");

			} else {
				log.trace("Failed to update GameSystem from DB");
				return false;
			}

		} catch (SQLException e) {
			log.warn("Error has occured while updating GameSystem from DB");
			log.warn(e);
			return false;
		}

		return true;
	}

	@Override
	public Boolean delete(GameSystem g) {
		log.trace("Deleting Game System [" + g.getConsoleName() + "] from DB");
		try (Connection conn = cu.getConnection()) {
			String sql = "delete from GameSystem where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, g.getId());
			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				log.trace("Game System: " + g.getConsoleName() + " has been deleted from DB");
			} else {
				log.trace("Cannot find GameSystem with such id");
				return false;
			}

		} catch (SQLException e) {
			log.warn("Error has occured while deleting GameSystem from DB");
			log.warn(e);
			return false;
		}

		return true;
	}

}
