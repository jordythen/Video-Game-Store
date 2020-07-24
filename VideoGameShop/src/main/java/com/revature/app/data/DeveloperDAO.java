package com.revature.app.data;

import java.util.List;

import com.revature.app.beans.Developer;

public interface DeveloperDAO extends GenericDAO<Developer>{

	//Add CRUD method for games that developer made
	
	//Customer can register as a developer so we add to the developer_person table
	public Boolean addPersonToDev(Integer personID, Integer devID);
	
	//Developer can add games so we update the developer_game to show correlation
	public Boolean addDevToGame(Integer devID, Integer gameID);
	
	//Retrieving all developers related to gameID
	public List<Developer> getAllDevForGameID(Integer gameID);
}
