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
import com.revature.app.beans.Developer;
import com.revature.app.beans.User;
import com.revature.app.services.DeveloperService;
import com.revature.app.services.DeveloperServiceImpl;
import com.revature.app.services.UserService;
import com.revature.app.services.UserServiceImpl;

@RestController
@RequestMapping(path="/account")
public class UserController {

	public static Logger log = Logger.getLogger(UserController.class);
	private UserService uServ;
	private DeveloperService devServ;
	
	@Autowired
	public UserController() {
		uServ = new UserServiceImpl();
		devServ = new DeveloperServiceImpl();
	}
	
	@GetMapping(path="/login")
	public ResponseEntity<User> checkIfLoggedIn(HttpSession session){
		log.info("Checking to see if Customer session is occupied");
		User u = (User) session.getAttribute("user");
		if(u != null) {
			log.trace("user already logged in");
		}else {
			log.trace("user is not logged in");
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(u);
	}
	
	@PostMapping(path="/register")
	public ResponseEntity<User> registerCustomerAccount(@RequestParam("firstname") String firstName,
			@RequestParam("lastname") String lastName,
			@RequestParam("username") String username,
			@RequestParam("password") String password){
		
		log.info("Registering user " + username);
		Customer temp = new Customer();
		temp.setFirstName(firstName);
		temp.setLastName(lastName);
		temp.setUsername(username);
		temp.setPassword(password);
		temp.setMoney(0.00);
		
		User responseUser = uServ.registerAccount(temp);
		if(responseUser == null) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(responseUser);
		
	}
	
	@PostMapping(path="/register/usernameVerification")
	public ResponseEntity<String> checkIfUsernameExist(@RequestParam("username") String username, HttpSession session){
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(path="/devReg")
	public ResponseEntity<User> registerDeveloper(
			@RequestParam("userID") Integer userID,
			@RequestParam("name") String devName,
			@RequestParam("desc") String devDesc){
		
		log.info("Registering developer "+ devName);
		Developer dev = new Developer();
		dev.setName(devName);
		dev.setDescription(devDesc);
		
		Integer developerID = devServ.add(dev);
		//This specify the relationship between a person a developer(because developer might be a team)
		Boolean success = ((DeveloperServiceImpl) devServ).addPersonToDev(userID, developerID);
		
		if(success) {
			return ResponseEntity.ok().build();
		}else {

			return ResponseEntity.status(404).build();
		}
	}
	
	
	@PostMapping(path="/login")
	public ResponseEntity<User> login(@RequestParam("user") String username, @RequestParam("pass") String password, HttpSession session){
		User u = (User) session.getAttribute("user");
		if(u != null) {
			//already logged in
			log.trace("user already logged in");
		}else {
			log.trace("User is not in session, attempting to log in with username " + username + " and password " + password);
			u = uServ.findByUsernameAndPassword(username, password);
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
	
	@DeleteMapping(path="/login")
	public ResponseEntity<Customer> logout(HttpSession session){
		session.invalidate();
		log.info("User has been logged out");
		return ResponseEntity.ok().build();
	}
	/*
	@PutMapping
	public ResponseEntity<Customer> update(HttpSession session, @RequestBody Customer u ){
		uServ.update(u);
		updateSessionUser(session);
		return ResponseEntity.ok(uServ.getById(u.getId()));
	}
	
	public void updateSessionUser(HttpSession session) {
		Customer u=(Customer) session.getAttribute("user");
		Customer u2=uServ.getById(u.getId());
		session.setAttribute("user", u2);
	}*/
	
}
