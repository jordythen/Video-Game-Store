package com.revature.app.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.app.beans.Publisher;

@Repository
public interface PublisherDAO extends JpaRepository<Publisher,Integer>{

	Publisher findByName(String name);
}
