package com.revature.app.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.app.beans.Developer;

@Repository
public interface DeveloperDAO extends JpaRepository<Developer,Integer> {

	Developer findByName(String name);
}
