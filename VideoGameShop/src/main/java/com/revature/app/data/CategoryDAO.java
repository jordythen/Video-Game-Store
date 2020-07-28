package com.revature.app.data;

import java.util.List;

import com.revature.app.beans.Category;

public interface CategoryDAO extends GenericDAO<Category>{
	public List<Category> getAllCategoryForGameID(Integer gameID);

}
