package com.revature.app.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.app.beans.Customer;
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
	
	@GetMapping 
	public ResponseEntity<Customer> checkIfLoggedIn(HttpSession session){
		log.info("Checking to see if Customer session is occupied");
		Customer u = (Customer) session.getAttribute("user");
		if(u != null) {
			log.trace("user already logged in");
		}else {
			log.trace("user is not logged in");
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(u);
	}
	
	@PostMapping(path="/register")
	public ResponseEntity<Customer> register(@RequestBody Customer u){
		
		log.info("Registering user "+ u);
		return ResponseEntity.ok(cServ.registerAccount(u));
		
	}
	
	@PostMapping
	public ResponseEntity<Customer> login(@RequestParam("user") String username, @RequestParam("pass") String password, HttpSession session){
		Customer u = (Customer) session.getAttribute("user");
		if(u != null) {
			//already logged in
			log.trace("user already logged in");
		}else {
			log.trace("User is not in session, attempting to log in with username " + username + " and password " + password);
			u = cServ.getByUsernameAndPassword(username, password);
			if (u != null) {
				log.info("User was found!");
				session.setAttribute("user", u);
			}else {
				log.info("No user found with username and password");
				return ResponseEntity.noContent().build();
			}
		}
		return ResponseEntity.ok(u);
	}
	
	@DeleteMapping
	public ResponseEntity<Customer> logout(HttpSession session){
		session.invalidate();
		log.info("User has been logged out");
		return ResponseEntity.ok().build();
	}
	
	@PutMapping
	public ResponseEntity<Customer> update(HttpSession session, @RequestBody Customer u ){
		cServ.update(u);
		updateSessionUser(session);
		return ResponseEntity.ok(cServ.getById(u.getId()));
	}
	
	public void updateSessionUser(HttpSession session) {
		Customer u=(Customer) session.getAttribute("user");
		Customer u2=cServ.getById(u.getId());
		session.setAttribute("user", u2);
	}
	
}