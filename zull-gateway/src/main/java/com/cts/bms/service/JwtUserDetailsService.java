package com.cts.bms.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.bms.feign.UserClient;
import com.cts.bms.jwt.model.JwtRequest;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	
	@Autowired
	private UserClient userClient;

	
	@Override
	public UserDetails loadUserByUsername(String pan) {
		JwtRequest response = userClient.getUserByPan(pan);
		
		if (response == null) {
			throw new UsernameNotFoundException("User not found with username: " + pan);
		}
		
		return new org.springframework.security.core.userdetails.User(response.getPan(), response.getPassword(),
				new ArrayList<>());
	}
}