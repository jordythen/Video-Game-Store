package com.revature.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.app.beans.Game;
import com.revature.app.services.CompleteGameService;
import com.revature.app.services.CompleteGameServiceImpl;

@RestController
@RequestMapping(path="/inventory")
public class GameController {

	public static Logger log = Logger.getLogger(GameController.class);
	private CompleteGameService cgServ;
	
	@Autowired
	public GameController() {
		cgServ = new CompleteGameServiceImpl();
	}
	
	@GetMapping(path="/games")
	public ResponseEntity<List<Game>> retrieveAllGamesLightweight(HttpSession session){
		log.trace("Retrieving all games from inventory...");
		List<Game> games = (List<Game>) session.getAttribute("games");
		if(games != null) {
			log.trace("Session already hold stored games");
		}else { //if session is not yet set up
			log.trace("Session does not hold stored games, retrieving from DB");
			games = cgServ.findAllBasic();
			session.setAttribute("games", games);
		}
		
		if(games.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(games);
	}
	
	@GetMapping(path="/game/{id}")
	public ResponseEntity<Game> retrieveGameByID(@PathVariable("id") Integer id){
		Game g = cgServ.findById(id);
		
		if(g.equals(null)) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(g);
	}
}
