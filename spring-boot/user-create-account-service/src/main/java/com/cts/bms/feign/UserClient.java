package com.cts.bms.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


// to hit the user microservice to know weather pan is registered or not
@FeignClient(url="localhost:9010/user", value="user")
public interface UserClient {
	
	@GetMapping("/{pan}/{contact}")
	public boolean verifyUser(@PathVariable String pan, @PathVariable String contact);
	
}