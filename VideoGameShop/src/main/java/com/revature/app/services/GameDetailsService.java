package com.revature.app.services;

import java.util.List;

import com.revature.app.beans.GameDetails;

public interface GameDetailsService extends GenericService<GameDetails> {
	public List<GameDetails> findAllDetailsForGameID(Integer gameID);
	public List<GameDetails> findAllDetailsForGameIDLightweight(Integer gameID);
}
