package com.revature.app.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.app.beans.Game;

@RestController
@RequestMapping(path="/inventory")
public class GameController {
	
	public static Logger log = Logger.getLogger(GameController.class);
	
	public ResponseEntity<List<Game>> retrieveAllGameTitlesAndDevs(){
		
		return null;
	}

}
