package com.revature.app.services;

import java.util.List;

import com.revature.app.beans.Category;

public interface CategoryService extends GenericService<Category>{

	public List<Category> findAllCategoriesForGameID(Integer gameID);
}
