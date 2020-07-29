package com.revature.app.services;

import java.util.List;

import com.revature.app.beans.Game;

public interface CompleteGameService extends GenericService<Game>{
	public List<Game> findAllBasic();
}
