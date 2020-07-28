package com.revature.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.app.beans.Category;
import com.revature.app.data.CategoryOracle;

public class CategoryServiceImpl implements CategoryService {

	private static CategoryOracle cDao;
	
	@Autowired
	public CategoryServiceImpl() {
		cDao = new CategoryOracle();
	}
	
	@Override
	public Integer add(Category t) {
		// TODO Auto-generated method stub
		return cDao.add(t);
	}

	@Override
	public Category findById(Integer id) {
		// TODO Auto-generated method stub
		return cDao.getById(id);
	}

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return cDao.getAll();
	}

	@Override
	public Boolean update(Category t) {
		// TODO Auto-generated method stub
		return cDao.update(t);
	}

	@Override
	public Boolean delete(Category t) {
		// TODO Auto-generated method stub
		return cDao.delete(t);
	}

	@Override
	public List<Category> findAllCategoriesForGameID(Integer gameID) {
		// TODO Auto-generated method stub
		return cDao.getAllCategoryForGameID(gameID);
	}

}
