package com.revature.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.app.beans.GameDetails;
import com.revature.app.data.GameDetailsOracle;

public class GameDetailsServiceImpl implements GameDetailsService {

	private static GameDetailsOracle gdDao;

	@Autowired
	public GameDetailsServiceImpl() {
		gdDao = new GameDetailsOracle();
	}
	
	@Override
	public Integer add(GameDetails t) {
		// TODO Auto-generated method stub
		return gdDao.add(t);
	}

	@Override
	public GameDetails findById(Integer id) {
		// TODO Auto-generated method stub
		return gdDao.getById(id);
	}

	@Override
	public List<GameDetails> findAll() {
		// TODO Auto-generated method stub
		return gdDao.getAll();
	}

	@Override
	public Boolean update(GameDetails t) {
		// TODO Auto-generated method stub
		return gdDao.update(t);
	}

	@Override
	public Boolean delete(GameDetails t) {
		// TODO Auto-generated method stub
		return gdDao.delete(t);
	}

	@Override
	public List<GameDetails> findAllDetailsForGameID(Integer gameID) {
		// TODO Auto-generated method stub
		return gdDao.getAllDetailsForGameID(gameID);
	}

	@Override
	public List<GameDetails> findAllDetailsForGameIDLightweight(Integer gameID) {
		// TODO Auto-generated method stub
		return gdDao.getAllDetailsForGameIDLightweight(gameID);
	}

}
