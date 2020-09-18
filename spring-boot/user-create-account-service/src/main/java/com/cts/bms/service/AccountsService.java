package com.cts.bms.service;

import java.util.List;

import com.cts.bms.model.Account;

public interface AccountsService {
	
	public Account saveAccountService(Account account, String contact) throws Exception;

	public boolean verifyAccountService(String pan, String accountNo);

	public List<Account> getAllAccountService(String pan);

}
