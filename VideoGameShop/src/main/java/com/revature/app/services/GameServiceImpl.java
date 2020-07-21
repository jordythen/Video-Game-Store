package com.revature.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.app.beans.Game;
import com.revature.app.data.GameOracle;

public class GameServiceImpl implements GameService{

	private static GameOracle gDao;
	
	@Autowired
	public GameServiceImpl() {
		gDao = new GameOracle();
	}
	
	@Override
	public Integer add(Game t) {
		// TODO Auto-generated method stub
		return gDao.add(t);
	}

	@Override
	public Game findById(Integer id) {
		// TODO Auto-generated method stub
		return gDao.getById(id);
	}

	@Override
	public List<Game> findAll() {
		// TODO Auto-generated method stub
		return gDao.getAll();
	}

	@Override
	public Boolean update(Game t) {
		// TODO Auto-generated method stub
		return gDao.update(t);
	}

	@Override
	public Boolean delete(Game t) {
		// TODO Auto-generated method stub
		return gDao.delete(t);
	}

}
