package com.revature.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.app.beans.GameSystem;
import com.revature.app.data.GameSystemOracle;

public class GameSystemServiceImpl implements GameSystemService {

	private static GameSystemOracle gsDao;
	
	@Autowired
	public GameSystemServiceImpl() {
		gsDao = new GameSystemOracle();
	}
	
	@Override
	public Integer add(GameSystem t) {
		// TODO Auto-generated method stub
		return gsDao.add(t);
	}

	@Override
	public GameSystem findById(Integer id) {
		// TODO Auto-generated method stub
		return gsDao.getById(id);
	}

	@Override
	public List<GameSystem> findAll() {
		// TODO Auto-generated method stub
		return gsDao.getAll();
	}

	@Override
	public Boolean update(GameSystem t) {
		// TODO Auto-generated method stub
		return gsDao.update(t);
	}

	@Override
	public Boolean delete(GameSystem t) {
		// TODO Auto-generated method stub
		return gsDao.delete(t);
	}

	@Override
	public List<GameSystem> findAllSystemForGameID(Integer gameID) {
		// TODO Auto-generated method stub
		return gsDao.getAllSystemForGameID(gameID);
	}

}
