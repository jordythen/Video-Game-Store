package com.revature.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.app.beans.Customer;
import com.revature.app.beans.Employee;
import com.revature.app.beans.User;
import com.revature.app.utils.ConnectionUtil;

public class UserOracle implements UserDAO{

	ConnectionUtil cu = ConnectionUtil.getConnectionUtil(); //Our singleton class to create db connection
	public static Logger log = Logger.getLogger(UserOracle.class);

	
	@Override
	public Integer add(User t) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		log.trace("Retrieving person by username and password");
		User u = null;
		try(Connection conn = cu.getConnection()){
			
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
				}else if (roleID.equals(2) || roleID.equals(3)) {
					log.trace("This person is a employee");
					u = new Employee();
				}
				
				u.setId(rs.getInt("id"));
				u.setUsername(username);
				u.setMoney(rs.getDouble("money"));
				u.setFirstName(rs.getString("firstName"));
				u.setLastName(rs.getString("lastName"));
				
			}else {
				log.trace("Cannot find person with username and password");
				return null;
			}
			
			
		}catch(SQLException e) {
			log.warn("Error has occured while retrieving user from DB");
			log.warn(e);
			
		}
		
		return u;
	}

}
