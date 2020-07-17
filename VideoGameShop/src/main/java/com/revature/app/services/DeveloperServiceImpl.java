package com.revature.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.app.beans.Developer;
import com.revature.app.data.DeveloperOracle;

public class DeveloperServiceImpl implements DeveloperService {

	private static DeveloperOracle dDao;

	@Autowired
	public DeveloperServiceImpl() {
		dDao = new DeveloperOracle();
	}
	@Override
	public Integer add(Developer t) {
		// TODO Auto-generated method stub
		return dDao.add(t);
	}
	
	public Boolean addPersonToDev(Integer personID, Integer devID) {
		return dDao.addPersonToDev(personID, devID);
	}

	@Override
	public Developer findById(Integer id) {
		// TODO Auto-generated method stub
		return dDao.getById(id);
	}

	@Override
	public List<Developer> findAll() {
		// TODO Auto-generated method stub
		return dDao.getAll();
	}

	@Override
	public Boolean update(Developer t) {
		// TODO Auto-generated method stub
		return dDao.update(t);
	}

	@Override
	public Boolean delete(Developer t) {
		// TODO Auto-generated method stub
		return dDao.delete(t);
	}

}
