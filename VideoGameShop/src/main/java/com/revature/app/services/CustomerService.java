package com.revature.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.app.beans.Customer;
import com.revature.app.data.CustomerDAO;

@Service
public class CustomerService implements GenericService<Customer>{

	CustomerDAO cDao;
	
	@Autowired
	public CustomerService(CustomerDAO c) {
		cDao = c;
	}
	
	@Override
	public Integer add(Customer t) {
		// TODO Auto-generated method stub
		
		return cDao.save(t).getId();
	}

	@Override
	public Customer getById(Integer id) {
		// TODO Auto-generated method stub
		return cDao.findById(id).get();
	}
	
	public Customer getByUsernameAndPassword(String username, String password) {
		return cDao.findByUsernameAndPassword(username, password);
	}
	
	public Customer registerAccount(Customer c) {
		int id = add(c);
		return getById(id);
	}

	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		return cDao.findAll();
	}

	@Override
	public void update(Customer t) {
		// TODO Auto-generated method stub
		Optional<Customer> customer = cDao.findById(t.getId());
		if(customer.isPresent()) {
			try {
				cDao.save(t);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(Customer t) {
		// TODO Auto-generated method stub
		cDao.delete(t);
	}

}
