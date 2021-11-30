package com.nagarro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.nagarro.entity.User;
import com.nagarro.service.RegistrationService;

@RestController
@CrossOrigin
public class RegistrationController {
@Autowired
private RegistrationService service;

/*
 * This API is used to store the user details in database with encode password
 * @Param User
 * @Return User
 */
 @PostMapping("/registerUser")
 public User registerUser(@RequestBody User user) throws Exception{
	String tempEmailId=user.getEmailId();
	if(tempEmailId!=null && !"".equals(tempEmailId))
	{
	 User userObj=	service.fetchUserByEmailId(tempEmailId);
	 if(userObj!=null)
	 {
		 throw new Exception("User with" +tempEmailId+ " is already exist"); 
	 }
	}
	User userObj=null;
	String password=user.getPassword();
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	String hashedPassword = passwordEncoder.encode(password);
    user.setPassword(hashedPassword);
	userObj=service.saveUser(user);
	return userObj;
 }

}
