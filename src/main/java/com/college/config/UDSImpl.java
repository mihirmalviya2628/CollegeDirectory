package com.college.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.college.dao.UserRepository;
import com.college.entities.User;


public class UDSImpl implements UserDetailsService {
	
	 @Autowired
     private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		
		User user= userRepository.getUserByEmail(userEmail);
		
		if(user ==null) {
			throw new UsernameNotFoundException("User not found");
		}
		CustomUserDetails customUserDetails =new CustomUserDetails(user);
		return customUserDetails;
	}

}
