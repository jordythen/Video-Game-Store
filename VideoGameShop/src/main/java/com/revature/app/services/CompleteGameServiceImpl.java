package com.revature.app.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.app.beans.Category;
import com.revature.app.beans.Developer;
import com.revature.app.beans.Game;
import com.revature.app.beans.GameDetails;
import com.revature.app.beans.GameSystem;
import com.revature.app.beans.Publisher;

public class CompleteGameServiceImpl implements CompleteGameService{

	private static GameService gameService;
	private static GameDetailsService gameDetailsService;
	private static CategoryService categoryService;
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
		categoryService = new CategoryServiceImpl();
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
		
		List<Publisher> publishers = publisherService.findAllPublishersForGameID(g.getId());
		g.setPublishers(publishers);
		
		List<GameSystem> gamesystems = gameSystemService.findAllSystemForGameID(g.getId());
		g.setSystems(gamesystems);
		
		List<GameDetails> gamedetails = gameDetailsService.findAllDetailsForGameID(g.getId());
		g.setDetails(gamedetails);
		
		List<Category> categories = categoryService.findAllCategoriesForGameID(g.getId());
		g.setCategory(categories);
		
		return g;
	}

	// Will retrieve just the basic information to display to inventory page
	// More lightweight operation
	@Override
	public List<Game> findAllBasic() {
		// TODO Auto-generated method stub
		List<Game> games = gameService.findAll();
		List<Game> completeGames = new LinkedList<Game>();
		
		for(Game g: games) {
			List<Developer> devs = devService.findAllDevForGameID(g.getId());
			g.setDevelopers(devs);
			/*
			List<Publisher> publishers = publisherService.findAllPublishersForGameID(g.getId());
			g.setPublishers(publishers);
			*/
			List<GameSystem> gamesystems = gameSystemService.findAllSystemForGameID(g.getId());
			g.setSystems(gamesystems);
			/*
			List<GameDetails> gamedetails = gameDetailsService.findAllDetailsForGameIDLightweight(g.getId());
			g.setDetails(gamedetails);
			
			List<Category> categories = categoryService.findAllCategoriesForGameID(g.getId());
			g.setCategory(categories);
			*/
			completeGames.add(g);
		}
		
		return completeGames;
	}

	
	@Override
	public List<Game> findAll() {
		// TODO Auto-generated method stub
		List<Game> games = gameService.findAll();
		List<Game> completeGames = new LinkedList<Game>();
		
		for(Game g: games) {
			List<Developer> devs = devService.findAllDevForGameID(g.getId());
			g.setDevelopers(devs);
			
			List<Publisher> publishers = publisherService.findAllPublishersForGameID(g.getId());
			g.setPublishers(publishers);
		
			List<GameSystem> gamesystems = gameSystemService.findAllSystemForGameID(g.getId());
			g.setSystems(gamesystems);
			
			List<GameDetails> gamedetails = gameDetailsService.findAllDetailsForGameIDLightweight(g.getId());
			g.setDetails(gamedetails);
			
			List<Category> categories = categoryService.findAllCategoriesForGameID(g.getId());
			g.setCategory(categories);
			
			completeGames.add(g);
		}
		
		return completeGames;
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
