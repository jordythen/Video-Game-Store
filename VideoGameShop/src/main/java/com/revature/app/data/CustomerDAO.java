package com.revature.app.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.app.beans.Customer;

@Repository
public interface CustomerDAO extends JpaRepository<Customer,Integer> {
	
	public Customer findByUsernameAndPassword(String username, String password);
}
