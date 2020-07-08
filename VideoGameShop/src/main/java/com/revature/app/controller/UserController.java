package com.revature.app.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.app.services.CustomerService;

@RestController
@RequestMapping(path="/account")
public class UserController {

	public static Logger log = Logger.getLogger(UserController.class);
	private CustomerService cServ;
	
	@Autowired
	public UserController(CustomerService u) {
		cServ = u;
	}
	
}
