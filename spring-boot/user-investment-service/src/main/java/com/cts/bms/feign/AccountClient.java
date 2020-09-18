package com.cts.bms.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// to call account microservice to verify a user with oparticular pan and contactNo is exist or not
@FeignClient(url="localhost:9010/account", value="account")
public interface AccountClient {
	
	@GetMapping("{pan}/{accountNo}")
	public boolean verifyAccount(@PathVariable String pan, @PathVariable String accountNo);

}
