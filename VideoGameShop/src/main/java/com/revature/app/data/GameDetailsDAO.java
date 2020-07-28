package com.revature.app.data;

import java.util.List;

import com.revature.app.beans.GameDetails;

public interface GameDetailsDAO extends GenericDAO<GameDetails>{
	public List<GameDetails> getAllDetailsForGameID(Integer gameID);

}
