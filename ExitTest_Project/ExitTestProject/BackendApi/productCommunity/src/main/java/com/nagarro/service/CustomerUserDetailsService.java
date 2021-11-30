package com.nagarro.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

//import com.nagarro.dao.UserRepository;

import com.nagarro.entity.User;
import com.nagarro.model.CustomerUserDetails;


@Service
public class CustomerUserDetailsService implements UserDetailsService {

@Autowired
private RegistrationService repository;

@Override
public CustomerUserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
final User user=this.repository.fetchUserByEmailId(emailId);
if(user==null) {
	return null;
}
else {
return new CustomerUserDetails(user);
}
}

}