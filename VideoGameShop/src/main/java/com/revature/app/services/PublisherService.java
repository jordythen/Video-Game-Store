package com.revature.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.app.beans.Publisher;
import com.revature.app.data.PublisherDAO;

@Service
public class PublisherService implements GenericService<Publisher> {

	PublisherDAO pDao;
	
	@Autowired
	public PublisherService(PublisherDAO p) {
		pDao = p;
	}
	
	@Override
	public Integer add(Publisher t) {
		// TODO Auto-generated method stub
		return pDao.save(t).getId();
	}

	@Override
	public Publisher getById(Integer id) {
		// TODO Auto-generated method stub
		return pDao.findById(id).get();
	}

	@Override
	public List<Publisher> getAll() {
		// TODO Auto-generated method stub
		return pDao.findAll();
	}

	public Publisher getByName(String name) {
		return pDao.findByName(name);
	}
	
	@Override
	public void update(Publisher t) {
		// TODO Auto-generated method stub
		Optional<Publisher> publisher = pDao.findById(t.getId());
		if(publisher.isPresent()) {
			try {
				pDao.save(t);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(Publisher t) {
		// TODO Auto-generated method stub
		pDao.delete(t);
	}

}
