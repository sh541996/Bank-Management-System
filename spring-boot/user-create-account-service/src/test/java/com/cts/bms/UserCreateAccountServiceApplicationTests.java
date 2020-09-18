package com.cts.bms;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.bms.feign.UserClient;
import com.cts.bms.model.Account;
import com.cts.bms.repository.AccountRepository;
import com.cts.bms.service.AccountsService;
import com.cts.bms.service.AccountsServiceImpl;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserCreateAccountServiceApplicationTests {

	@Mock
	UserClient userClient;
	
	@Mock
	AccountRepository accountRepository;
	
	@InjectMocks
	AccountsService accountService =  new AccountsServiceImpl();
	
	@Test
	public void saveAccountServiceTest() throws Exception {
		Account account_1 = new Account("1234567874", "utib000", "axis", "xyzuv99w", "IHBSR5420B");
		
		when(userClient.verifyUser("IHBSR5420B", "9852625560")).thenReturn(true);
		when(accountRepository.countByPan("IHBSR5420B")).thenReturn(2);
		when(accountRepository.save(account_1)).thenReturn(account_1);
		
		Account account_2 = accountService.saveAccountService(account_1, "9852625560");
		
		assertEquals(account_1, account_2);
	}
	
	@Test
	public void verifyAccountServiceTest() {
		
		Account account_1 = new Account("1234567874", "utib000", "axis", "xyzuv99w", "IHBSR5420B");
		
		when(accountRepository.verifyAccount("IHBSR5420B", "1234567874")).thenReturn(1);
		
		boolean result = accountService.verifyAccountService("IHBSR5420B", "1234567874");
		
		assertEquals(true, result);
	}
	
	@Test
	public void getAllAccountServiceTest() {
		
		List<Account> account_1 = new ArrayList();
		account_1.add(new Account("1234567874", "utib000", "axis", "xyzuv99w", "IHBSR5420B"));
		account_1.add(new Account("1234567856", "utib087", "hdfc", "xyzuv99w", "IHBSR5420B"));
		account_1.add(new Account("1236767856", "sbib087", "sbi", "xghcf8899w", "IHBSR5420B"));
		
		when( accountRepository.findAllByPan("IHBSR5420B")).thenReturn(account_1);
		
		List<Account> account_2 = accountService.getAllAccountService("IHBSR5420B");
		
		assertEquals(account_1, account_2);
		
	}
	
}
