package com.revature.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.app.beans.GameSystem;
import com.revature.app.data.GameSystemDAO;

@Service
public class GameSystemService implements GenericService<GameSystem> {

	GameSystemDAO gsDao;
	
	@Autowired
	public GameSystemService(GameSystemDAO g) {
		gsDao = g;
	}
	
	@Override
	public Integer add(GameSystem t) {
		// TODO Auto-generated method stub
		return gsDao.save(t).getId();
	}

	@Override
	public GameSystem getById(Integer id) {
		// TODO Auto-generated method stub
		return gsDao.findById(id).get();
	}

	@Override
	public List<GameSystem> getAll() {
		// TODO Auto-generated method stub
		return gsDao.findAll();
	}

	@Override
	public void update(GameSystem t) {
		// TODO Auto-generated method stub
		Optional<GameSystem> gs = gsDao.findById(t.getId());
		if(gs.isPresent()) {
			try {
				gsDao.save(t);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(GameSystem t) {
		// TODO Auto-generated method stub
		gsDao.delete(t);
	}

}
