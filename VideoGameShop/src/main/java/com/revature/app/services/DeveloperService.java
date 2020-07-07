package com.revature.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.app.beans.Developer;
import com.revature.app.data.DeveloperDAO;
@Service
public class DeveloperService implements GenericService<Developer> {

	DeveloperDAO dDao;
	
	@Autowired
	public DeveloperService(DeveloperDAO d) {
		dDao = d;
	}
	
	@Override
	public Integer add(Developer t) {
		// TODO Auto-generated method stub
		return dDao.save(t).getId();
	}

	@Override
	public Developer getById(Integer id) {
		// TODO Auto-generated method stub
		return dDao.findById(id).get();
	}

	@Override
	public List<Developer> getAll() {
		// TODO Auto-generated method stub
		return dDao.findAll();
	}
	
	public Developer getByName(String name) {
		return dDao.findByName(name);
	}

	@Override
	public void update(Developer t) {
		// TODO Auto-generated method stub
		Optional<Developer> dev = dDao.findById(t.getId());
		if(dev.isPresent()) {
			try {
				dDao.save(t);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(Developer t) {
		// TODO Auto-generated method stub
		dDao.delete(t);
	}

}
