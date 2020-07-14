package com.revature.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.app.beans.Customer;
import com.revature.app.beans.User;
import com.revature.app.data.UserOracle;

public class UserServiceImpl implements UserService {

	private static UserOracle uDao;

	@Autowired
	public UserServiceImpl() {
		uDao = new UserOracle();
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return uDao.getByUsernameAndPassword(username, password);
	}

	@Override
	public Integer add(User u) {
		// TODO Auto-generated method stub
		return uDao.add(u);
	}

	@Override
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return uDao.getById(id);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return uDao.getAll();
	}

	@Override
	public Boolean update(User u) {
		// TODO Auto-generated method stub
		return uDao.update(u);
	}

	@Override
	public Boolean delete(User u) {
		// TODO Auto-generated method stub
		return uDao.delete(u);
	}

	@Override
	public User registerAccount(User u) {
		// TODO Auto-generated method stub

		return findById(add(u));
	}

}
