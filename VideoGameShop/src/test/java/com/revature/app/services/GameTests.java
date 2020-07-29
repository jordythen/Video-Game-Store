package com.revature.app.services;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.revature.app.beans.Game;
import com.revature.app.beans.User;

public class GameTests {

	public GameService gtest;
	
	@Test
	public void testAddGame() {
		Game g = new Game();
		LocalDateTime time = LocalDateTime.now();
		g.setName("Love All Humans");
		g.setDateReleased(time);
		g.setEsrbRating("E");
		g.setPlayerLimit("1-2");
		
		assertEquals("Love All Humans", gtest.findById(gtest.add(g)).getName());
		
		
	}
	
	@Before
	public void initializeService() {
		gtest = new GameServiceImpl();
	}
	
}
