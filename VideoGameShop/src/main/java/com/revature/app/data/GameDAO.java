package com.revature.app.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.app.beans.Game;

@Repository
public interface GameDAO extends JpaRepository<Game,Integer> {

	Game findByName(String name);
}
