package com.revature.app.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.app.beans.User;
import com.revature.app.data.UserOracle;

public class UserServiceImpl implements UserService{

	private static UserOracle uDao;
	
	
	@Autowired
	public UserServiceImpl() {
		uDao = new UserOracle();
	}

	public static void main(String[] args) {
		UserServiceImpl us = new UserServiceImpl();
		System.out.println(us.findByUsernameAndPassword("jordythen", "pass"));
	}
	
	@Override
	public User findByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return uDao.getByUsernameAndPassword(username, password);
	}

}
