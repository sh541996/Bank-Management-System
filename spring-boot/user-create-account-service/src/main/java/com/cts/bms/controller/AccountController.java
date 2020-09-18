package com.cts.bms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.bms.feign.UserClient;
import com.cts.bms.model.Account;
import com.cts.bms.service.AccountsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class AccountController {
	
	@Autowired
	AccountsService accountService;
	
	@Autowired
	UserClient userClient;
	
	private Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	// to save the account details in db
	@PostMapping("/save/{contact}")
	public ResponseEntity<?> saveAccount(@Valid @RequestBody Account account, @PathVariable String contact) throws Exception {
		
		// loging
		String methodName = "saveAccount()";
		logger.info(methodName+" called");
		
		return ResponseEntity.ok(accountService.saveAccountService(account, contact));

	}
	
	// to verify the particular account is present or not in db
	@GetMapping("/{pan}/{accountNo}")
	public boolean verifyAccount(@PathVariable String pan, @PathVariable String accountNo) {
		
		// loging
		String methodName = "verifyAccount()";
		logger.info(methodName+" called");
		
		return accountService.verifyAccountService(pan, accountNo);
	}
	
	// to get list of all account registered using a particualr pan
	@GetMapping("/{pan}")
	public List<Account> getAllAccount(@PathVariable String pan) {
		
		// loging
		String methodName = "getAllAccount()";
		logger.info(methodName+" called");
		
		return accountService.getAllAccountService(pan);
	}

}
