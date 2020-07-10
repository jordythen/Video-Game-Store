package com.revature.app.data;

import com.revature.app.beans.User;

public interface UserDAO extends GenericDAO<User> {

	public User getByUsernameAndPassword(String username, String password);
}
