package com.nagarro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.entity.User;
import com.nagarro.repository.RegistrationRepository;
import com.nagarro.serviceInterface.RegistrationServiceInterface;

@Service
public class RegistrationService implements RegistrationServiceInterface {
 
	@Autowired
	private RegistrationRepository repo;
	
	//This method is used to save user
	@Override
	public User saveUser(User user)
	{
	return repo.save(user);	
	}
	
	//This method is used to get User with help of user emailId
	@Override
	public User fetchUserByEmailId(String emailId) {
		return repo.findByEmailId(emailId);
	}
	
	//This method is used to get count of all Registrated User
	@Override
	public Long countAllRegistrated()
	{
		return repo.count();
	}
    
	
}
