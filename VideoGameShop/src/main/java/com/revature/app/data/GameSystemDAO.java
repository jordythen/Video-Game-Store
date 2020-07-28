package com.revature.app.data;

import java.util.List;

import com.revature.app.beans.GameSystem;

public interface GameSystemDAO extends GenericDAO<GameSystem>{
	
	//Retrieving all game systems that is related to game
	public List<GameSystem> getAllSystemForGameID(Integer gameID);
}
