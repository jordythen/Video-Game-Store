package com.revature.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.app.beans.Customer;
import com.revature.app.beans.Employee;
import com.revature.app.beans.User;
import com.revature.app.utils.ConnectionUtil;

public class UserOracle implements UserDAO {

	ConnectionUtil cu = ConnectionUtil.getConnectionUtil(); // Our singleton class to create db connection
	public static Logger log = Logger.getLogger(UserOracle.class);

	@Override
	public Integer add(User t) {
		// TODO Auto-generated method stub
		Integer key = 0;
		log.trace("Adding user " + t.getFirstName() + " " + t.getLastName() + " into the database.");
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);

			// Gathering role ID based on User's role/class
			Integer roleID = null;
			if (t instanceof Customer) {
				roleID = 1;
			} else if (t instanceof Employee) {
				String role = ((Employee) t).getRole();
				if (role.equals("employee")) {
					roleID = 2;
				} else if (role.equals("manager")) {
					roleID = 3;
				}
			}

			String sql = "insert into person(roleid, username, passwd, firstname, lastname, money) "
					+ "values (?, ?, ?, ?, ?, ?)";
			String[] keys = { "id" };
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, roleID);
			pstmt.setString(2, t.getUsername());
			pstmt.setString(3, t.getPassword());
			pstmt.setString(4, t.getFirstName());
			pstmt.setString(5, t.getLastName());
			pstmt.setDouble(6, t.getMoney());
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				log.trace(
						"Successfully added user " + t.getFirstName() + " " + t.getLastName() + " into the database.");
				key = rs.getInt(1);
				conn.commit();
			} else {
				log.trace("Failed to add user " + t.getFirstName() + " " + t.getLastName() + " into the database.");
				conn.rollback();
			}

		} catch (SQLException e) {
			log.warn("Error has occured while adding user into the DB");
			log.warn(e);
		}

		return key;
	}

	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		log.trace("Retrieving person with ID: " + id + " from DB");
		User u = null;

		try (Connection conn = cu.getConnection()) {
			String sql = "select * from PERSON where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				log.trace("person with ID: " + id + " was found!");

				Integer roleID = rs.getInt("roleID");
				if (roleID.equals(1)) {
					log.trace("This person is a customer");
					u = new Customer();
				} else if (roleID.equals(2)) {
					log.trace("This person is a manager");
					u = new Employee();
					((Employee) u).setRole("employee");
				} else if (roleID.equals(3)) {
					log.trace("This person is a employee");
					u = new Employee();
					((Employee) u).setRole("manager");
				}

				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("passwd"));
				u.setMoney(rs.getDouble("money"));
				u.setFirstName(rs.getString("firstName"));
				u.setLastName(rs.getString("lastName"));

			} else {
				log.trace("Cannot find person with ID: " + id);
				return null;
			}

		} catch (SQLException e) {
			log.warn("Error has occured while retrieving user from DB");
			log.warn(e);

		}

		return u;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		log.trace("Retrieving all users from the person table");
		List<User> users = new LinkedList<User>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from person";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				User u = null;

				Integer roleID = rs.getInt("roleID");
				if (roleID.equals(1)) {
					log.trace("This person is a customer");
					u = new Customer();
				} else if (roleID.equals(2)) {
					log.trace("This person is a manager");
					u = new Employee();
					((Employee) u).setRole("employee");
				} else if (roleID.equals(3)) {
					log.trace("This person is a employee");
					u = new Employee();
					((Employee) u).setRole("manager");
				}

				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("passwd"));
				u.setMoney(rs.getDouble("money"));
				u.setFirstName(rs.getString("firstName"));
				u.setLastName(rs.getString("lastName"));

				users.add(u);
			}
		} catch (SQLException e) {
			log.warn("Error has occured while retrieving user from DB");
			log.warn(e);
		}

		if (!users.isEmpty()) {
			return users;
		}
		return null;
	}

	public List<String> getAllUsername() {
		log.trace("Retrieving all usernames from the person table");
		List<String> usernames = new LinkedList<String>();
		try (Connection conn = cu.getConnection()) {

			String sql = "select username from person";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String tempUsername = rs.getString("username");
				usernames.add(tempUsername);
			}

		} catch (SQLException e) {
			log.warn("Error has occured while retrieving user from DB");
			log.warn(e);
		}
		
		if(!usernames.isEmpty()) {
			return usernames;
		}
		return null;
	}

	@Override
	public Boolean update(User t) {
		// TODO Auto-generated method stub
		log.trace("Updating user(" + t.getUsername() + ") with ID: " + t.getId());

		// Gathering role ID based on User's role/class
		Integer roleID = null;
		if (t instanceof Customer) {
			roleID = 1;
		} else if (t instanceof Employee) {
			String role = ((Employee) t).getRole();
			if (role.equals("employee")) {
				roleID = 2;
			} else if (role.equals("manager")) {
				roleID = 3;
			}
		}

		try (Connection conn = cu.getConnection()) {
			String sql = "update person set " + "roleid = ?, username = ?, passwd = ?, "
					+ "firstname = ?, lastname = ?, money = ? " + "where id = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roleID);
			pstmt.setString(2, t.getUsername());
			pstmt.setString(3, t.getPassword());
			pstmt.setString(4, t.getFirstName());
			pstmt.setString(5, t.getLastName());
			pstmt.setDouble(6, t.getMoney());
			pstmt.setInt(7, t.getId());

			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				log.trace("User(" + t.getUsername() + ") with ID: " + t.getId() + " has been updated!");
			} else {
				log.trace("Cannot find person with ID: " + t.getId());
				return false;
			}

		} catch (SQLException e) {
			log.warn("Error has occured while updating user from DB");
			log.warn(e);
			return false;
		}
		// Returns true if successfully updated
		return true;
	}

	@Override
	public Boolean delete(User t) {
		// TODO Auto-generated method stub
		log.trace("Deleting user(" + t.getUsername() + ") with ID: " + t.getId() + " from DB");
		try (Connection conn = cu.getConnection()) {

			String sql = "delete from person where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());

			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				log.trace("User(" + t.getUsername() + ") with ID: " + t.getId() + " has been deleted!");
			} else {
				log.trace("Cannot find person with ID: " + t.getId());
				return false;
			}

		} catch (SQLException e) {
			log.warn("Error has occured while deleting user from DB");
			log.warn(e);
			return false;
		}

		return true;

	}

	@Override
	public User getByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		log.trace("Retrieving person by username and password");
		User u = null;
		try (Connection conn = cu.getConnection()) {

			String sql = "select * from PERSON where username = ? AND passwd = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				log.trace("Person with username " + username + " was found!");

				Integer roleID = rs.getInt("roleID");
				if (roleID.equals(1)) {
					log.trace("This person is a customer");
					u = new Customer();
				} else if (roleID.equals(2) || roleID.equals(3)) {
					log.trace("This person is a employee");
					u = new Employee();
				}

				u.setId(rs.getInt("id"));
				u.setUsername(username);
				u.setMoney(rs.getDouble("money"));
				u.setFirstName(rs.getString("firstName"));
				u.setLastName(rs.getString("lastName"));

			} else {
				log.trace("Cannot find person with username and password");
				return null;
			}

		} catch (SQLException e) {
			log.warn("Error has occured while retrieving user from DB");
			log.warn(e);

		}

		return u;
	}

}
