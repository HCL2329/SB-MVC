package com.lti.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.register.model.User;
import com.lti.register.repo.UserRepository;

@RestController
public class RegistrationController {
	@Autowired
	UserRepository repository;
	
	@RequestMapping("/register-user/{userName}/{password}")
	public String registerUser(@PathVariable("userName")String userName,@PathVariable("password") String password) {
		
		User u=new User();
		
		u.setId(18);
		u.setName(userName);
		u.setPassword(password);
		
		repository.save(u);
		
		return "Registration Succesfull";
		
	}

}
