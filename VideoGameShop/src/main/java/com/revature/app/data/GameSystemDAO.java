package com.revature.app.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.app.beans.GameSystem;

@Repository
public interface GameSystemDAO extends JpaRepository<GameSystem,Integer> {

}
