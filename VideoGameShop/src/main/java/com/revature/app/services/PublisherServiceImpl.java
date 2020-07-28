package com.revature.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.app.beans.Publisher;
import com.revature.app.data.PublisherOracle;

public class PublisherServiceImpl implements PublisherService {

	private static PublisherOracle pDao;
	
	@Autowired
	public PublisherServiceImpl() {
		pDao = new PublisherOracle();
	}
	
	@Override
	public Integer add(Publisher t) {
		// TODO Auto-generated method stub
		return pDao.add(t);
	}

	@Override
	public Publisher findById(Integer id) {
		// TODO Auto-generated method stub
		return pDao.getById(id);
	}

	@Override
	public List<Publisher> findAll() {
		// TODO Auto-generated method stub
		return pDao.getAll();
	}

	@Override
	public Boolean update(Publisher t) {
		// TODO Auto-generated method stub
		return pDao.update(t);
	}

	@Override
	public Boolean delete(Publisher t) {
		// TODO Auto-generated method stub
		return pDao.delete(t);
	}

	@Override
	public List<Publisher> findAllPublishersForGameID(Integer gameID) {
		// TODO Auto-generated method stub
		return pDao.getAllPublishersForGameID(gameID);
	}

}
