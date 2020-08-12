package com.cts.bms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.bms.feign.UserClient;
import com.cts.bms.jwt.model.JwtRequest;

@RestController
public class EmployeeController {
	
    @RequestMapping({ "/greeting" })
    public String welcomePage() {
        return "Welcome!";
    }
    
    @Autowired
    UserClient userClient;
    
    @RequestMapping("/register/{pan}")
    public JwtRequest getPan(@PathVariable String pan) {
    	JwtRequest jwtRequest = userClient.getUserByPan(pan);
    	return jwtRequest;
    }
}