package com.revature.app.services;

import com.revature.app.beans.User;

public interface UserService extends GenericService<User>{

	public User findByUsernameAndPassword(String username, String password);
	public User registerAccount(User u);
}
