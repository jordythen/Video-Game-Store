package com.revature.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.app.beans.Developer;
import com.revature.app.beans.Game;

public class CompleteGameServiceImpl implements CompleteGameService{

	private static GameService gameService;
	private static GameDetailsService gameDetailsService;
	private static DeveloperService devService;
	private static PublisherService	publisherService;
	private static GameSystemService  gameSystemService;
	
	@Autowired
	public CompleteGameServiceImpl() {
		gameService = new GameServiceImpl();
		gameSystemService = new GameSystemServiceImpl();
		gameDetailsService = new GameDetailsServiceImpl();
		devService = new DeveloperServiceImpl();
		publisherService = new PublisherServiceImpl();
	}
	
	@Override
	public Integer add(Game t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game findById(Integer id) {
		// TODO Auto-generated method stub
		Game g = gameService.findById(id);
		
		//Retrieve developers related to this game ID
		List<Developer> devs = devService.findAllDevForGameID(g.getId());
		g.setDevelopers(devs);
		
		
		
		return null;
	}

	@Override
	public List<Game> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Game t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Game t) {
		// TODO Auto-generated method stub
		return null;
	}

}
