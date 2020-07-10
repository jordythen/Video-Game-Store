package com.revature.app.services;

import com.revature.app.beans.User;

public interface UserService {

	public User findByUsernameAndPassword(String username, String password);
}
