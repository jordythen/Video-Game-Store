package com.revature.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.app.beans.GameDetails;
import com.revature.app.data.GameDetailsDAO;

public class GameDetailsService implements GenericService<GameDetails> {

	GameDetailsDAO gDao;
	
	@Autowired
	public GameDetailsService(GameDetailsDAO g) {
		gDao = g;
	}
	
	@Override
	public Integer add(GameDetails t) {
		// TODO Auto-generated method stub
		return gDao.save(t).getId();
	}

	@Override
	public GameDetails getById(Integer id) {
		// TODO Auto-generated method stub
		return gDao.findById(id).get();
	}

	@Override
	public List<GameDetails> getAll() {
		// TODO Auto-generated method stub
		return gDao.findAll();
	}

	@Override
	public void update(GameDetails t) {
		// TODO Auto-generated method stub
		Optional<GameDetails> gDetails = gDao.findById(t.getId());
		if(gDetails.isPresent()) {
			try {
				gDao.save(t);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(GameDetails t) {
		// TODO Auto-generated method stub
		gDao.delete(t);
	}

	
}
