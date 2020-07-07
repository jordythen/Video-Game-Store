package com.revature.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.app.beans.Game;
import com.revature.app.data.GameDAO;

@Service
public class GameService implements GenericService<Game> {

	private GameDAO gDao;
	
	@Autowired
	public GameService(GameDAO g) {
		gDao = g;
	}
	
	@Override
	public Integer add(Game t) {
		// TODO Auto-generated method stub
		return gDao.save(t).getId();
	}

	@Override
	public Game getById(Integer id) {
		// TODO Auto-generated method stub
		return gDao.findById(id).get();
	}

	@Override
	public List<Game> getAll() {
		// TODO Auto-generated method stub
		return gDao.findAll();
	}
	
	public Game getByName(String name) {
		return gDao.findByName(name);
	}

	@Override
	public void update(Game t) {
		// TODO Auto-generated method stub
		Optional<Game> game = gDao.findById(t.getId());
		if(game.isPresent()) {
			try {
				gDao.save(t);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(Game t) {
		// TODO Auto-generated method stub
		gDao.delete(t);
	}
	

}
