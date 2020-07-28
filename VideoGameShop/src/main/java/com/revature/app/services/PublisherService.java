package com.revature.app.services;

import java.util.List;

import com.revature.app.beans.Publisher;

public interface PublisherService extends GenericService<Publisher> {
	public List<Publisher> findAllPublishersForGameID(Integer gameID);

}
