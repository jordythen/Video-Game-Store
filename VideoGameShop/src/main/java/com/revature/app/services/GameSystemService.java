package com.revature.app.services;

import java.util.List;

import com.revature.app.beans.GameSystem;

public interface GameSystemService extends GenericService<GameSystem>{
	public List<GameSystem> findAllSystemForGameID(Integer gameID);
}
