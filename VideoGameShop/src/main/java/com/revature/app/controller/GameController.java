package com.revature.app.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	/*
	public ResponseEntity<List<Game>> retrieveAllGameTitlesAndDevs(){
		
		return null;
	}*/
	
	@GetMapping(path="/game/{id}")
	public ResponseEntity<Game> retrieveGameByID(@PathVariable("id") Integer id){
		Game g = cgServ.findById(id);
		
		if(g.equals(null)) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(g);
	}
}
