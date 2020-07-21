package com.revature.app.data;

import com.revature.app.beans.Publisher;

public interface PublisherDAO extends GenericDAO<Publisher>{

	public Boolean addPublisherToGame(Integer publisherID, Integer gameID);
}