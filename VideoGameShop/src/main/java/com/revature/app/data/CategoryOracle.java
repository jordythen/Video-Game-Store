package com.revature.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.app.beans.Category;
import com.revature.app.utils.ConnectionUtil;

public class CategoryOracle implements CategoryDAO {
	
	ConnectionUtil cu = ConnectionUtil.getConnectionUtil(); //Our singleton class to create db connection
	public static Logger log = Logger.getLogger(CategoryOracle.class);

	@Override
	public Integer add(Category t) {
		// TODO Auto-generated method stub
		Integer key = 0;
		log.trace("Adding category [" + t.getCategoryName() + "] into the DB");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into gamecategory(name, description) "
					+ "values (?,?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getCategoryName());
			pstmt.setString(2, t.getCategoryDescription());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				log.trace("Successfully added category " + t.getCategoryName() + " into the DB.");
				key = rs.getInt(1);
				conn.commit();
			}else {
				log.trace("Failed to add category " + t.getCategoryName() + " into the DB");
				conn.rollback();
			}
			
			
		}catch(SQLException e) {
			log.warn("Error has occured while adding category into the DB");
			log.warn(e);
		}
		
		return key;
	}

	@Override
	public Category getById(Integer id) {
		// TODO Auto-generated method stub
		log.trace("Retrieving category with ID: " + id + " from DB");
		Category u = new Category();
		try(Connection conn = cu.getConnection()){
			
			String sql = "select * from gamecategory where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				log.trace("Successfully retrieved category with id: " + id);
				u.setId(rs.getInt("id"));
				u.setCategoryName(rs.getString("name"));
				u.setCategoryDescription(rs.getString("description"));
			}else {
				log.trace("Cannot find category with ID: " + id);
				return null;
			}
			
		} catch (SQLException e) {
			log.warn("Error has occured while retrieving category from DB");
			log.warn(e);
		}
		
		return u;
	}

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		log.trace("Retrieving all categories from the person table");
		List<Category> categories = new LinkedList<Category>();
		try(Connection conn = cu.getConnection()){
			String sql = "select * from gamecategory";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Category c = new Category();
				c.setId(rs.getInt("id"));
				c.setCategoryName(rs.getString("name"));
				c.setCategoryDescription(rs.getString("description"));
				
				categories.add(c);
			}
			
			
		}catch(SQLException e) {
			log.warn("Error has occured while retrieving categories from DB");
			log.warn(e);
		}
		
		if(!categories.isEmpty()) {
			return categories;
		}
		return null;
	}

	@Override
	public Boolean update(Category t) {
		// TODO Auto-generated method stub
		log.trace("Updating category " + t.getCategoryName() + " from DB");
		try(Connection conn = cu.getConnection()){
			String sql = "update gamecategory set name = ?, description = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getCategoryName());
			pstmt.setString(2, t.getCategoryDescription());
			pstmt.setInt(3, t.getId());
			int rs = pstmt.executeUpdate();
			if(rs > 0) {
				log.trace("Category ("+t.getCategoryName()+") has been updated!");
				
			}else {
				log.trace("Failed to update category from DB");
				return false;
			}
			
		}catch(SQLException e) {
			log.warn("Error has occured while updating category from DB");
			log.warn(e);
			return false;
		}
		
		return true;
	}

	@Override
	public Boolean delete(Category t) {
		// TODO Auto-generated method stub
		log.trace("Deleting category [" + t.getCategoryName() + "] from DB");
		try(Connection conn = cu.getConnection()){
			String sql = "delete from gamecategory where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			int rs = pstmt.executeUpdate();
			if(rs > 0) {
				log.trace("Category: " + t.getCategoryName() + " has been deleted from DB");
			}else {
				log.trace("Cannot find category with such id");
				return false;
			}
			
			
		}catch(SQLException e) {
			log.warn("Error has occured while deleting category from DB");
			log.warn(e);
			return false;
		}
		
		return true;
	}

}
