package com.revature.app.data;

import java.util.List;

import com.revature.app.beans.Publisher;

public interface PublisherDAO extends GenericDAO<Publisher>{

	public Boolean addPublisherToGame(Integer publisherID, Integer gameID);
	public List<Publisher> getAllPublishersForGameID(Integer gameID);

}
