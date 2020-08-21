package com.cts.bms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bms.exception.AccountAlreadyExist;
import com.cts.bms.exception.InvalidPanContact;
import com.cts.bms.exception.MaximumAccount;
import com.cts.bms.feign.UserClient;
import com.cts.bms.model.Account;
import com.cts.bms.repository.AccountRepository;

@Service
public class AccountsServiceImpl implements AccountsService {
	
	@Autowired
	UserClient userClient;
	
	@Autowired
	AccountRepository accountRepository;
	
	private Logger logger = LoggerFactory.getLogger(AccountsServiceImpl.class);

	// to save the account details of bank in db
	@Override
	public Account saveAccountService(Account account, String contact) throws InvalidPanContact, AccountAlreadyExist, MaximumAccount {
		
		// loging
		String methodName = "saveAccountService()";
		logger.info(methodName+" called");
		
		// to check is user with pan=account.getPan() and contact is exist in user table or not
		if( userClient.verifyUser(account.getPan().toUpperCase(), contact) ) {
			
			// to check is the account registerd by a user is less than 4 or not
			if(accountRepository.countByPan(account.getPan())<4) {
			
				// to check is given bank account provided by user is already registered or not
				if(accountRepository.findByAccountNo(account.getAccountNo()) != null) 
					
					// when account is already registerd in db
					throw new AccountAlreadyExist("account no "+account.getAccountNo()+" is already register");					
		
				else return accountRepository.save(account);
				
				}
			
			// when user already have 4 account 
			else throw new MaximumAccount("you cant register more than 4 account");
		}
		
		// when pan or contact no is not correct
		else throw new InvalidPanContact("please give registerd pan and contact no");
				
	}

	// to check is accountNo with respective pan is registered or not at a time of investment
	@Override
	public boolean verifyAccountService(String pan, String accountNo) {
		
		// loging
		String methodName = "verifyAccountService()";
		logger.info(methodName+" called");
		
		Account account = accountRepository.findByAccountNo(accountNo);
		// to check is account is registered in db or not
		if(account != null) {
			
			if(pan.equalsIgnoreCase(account.getPan())) return true;	
			
			else return false;
		}
		
		else return false;
	}

	// to get all account registered using a particular pan
	@Override
	public List<Account> getAllAccountService(String pan) {
		
		// loging
		String methodName = "verifyAccountService()";
		logger.info(methodName+" called");
		
		List<Account> account = accountRepository.findAllByPan(pan);
		return account;
	}
}
