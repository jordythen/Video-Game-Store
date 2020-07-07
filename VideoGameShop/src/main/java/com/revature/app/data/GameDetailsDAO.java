package com.revature.app.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.app.beans.GameDetails;

public interface GameDetailsDAO extends JpaRepository<GameDetails,Integer> {

}
