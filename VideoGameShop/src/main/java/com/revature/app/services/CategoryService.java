package com.revature.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.app.beans.Category;
import com.revature.app.data.CategoryDAO;

public class CategoryService implements GenericService<Category>{

	private CategoryDAO cDao;
	
	@Autowired
	public CategoryService(CategoryDAO c) {
		cDao = c;
	}
	
	@Override
	public Integer add(Category t) {
		// TODO Auto-generated method stub
		return cDao.save(t).getId();
	}

	@Override
	public Category getById(Integer id) {
		// TODO Auto-generated method stub
		return cDao.findById(id).get();
	}

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		return cDao.findAll();
	}
	
	public Category getByName(String name) {
		return cDao.findByName(name);
	}

	@Override
	public void update(Category t) {
		// TODO Auto-generated method stub
		Optional<Category> category = cDao.findById(t.getId());
		if(category.isPresent()) {
			try {
				cDao.save(t);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(Category t) {
		// TODO Auto-generated method stub
		cDao.delete(t);
	}

}
