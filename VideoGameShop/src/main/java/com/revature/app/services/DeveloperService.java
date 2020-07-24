package com.revature.app.services;

import java.util.List;

import com.revature.app.beans.Developer;

public interface DeveloperService extends GenericService<Developer> {
	public List<Developer> findAllDevForGameID(Integer gameID);
}
