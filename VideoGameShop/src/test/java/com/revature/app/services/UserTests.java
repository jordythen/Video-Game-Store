package com.revature.app.services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.app.beans.Customer;
import com.revature.app.beans.User;

public class UserTests {

	public UserService utest;
	
	@Test
	public void testGetByUsernameAndPassword() {
		User customer = new Customer();
		
		customer.setFirstName("Jordy");
		
		User testCustomer = utest.findByUsernameAndPassword("jordythen", "pass");
		
		System.out.println("THIS IS CUSTOMER: " + testCustomer);
		
		assertEquals(customer.getFirstName(), testCustomer.getFirstName());
		
	}
	
	@Before
	public void initializeService() {
		utest = new UserServiceImpl();
	}
	
}
