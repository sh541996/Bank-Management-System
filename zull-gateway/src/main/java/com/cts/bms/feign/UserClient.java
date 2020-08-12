package com.cts.bms.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.bms.jwt.model.JwtRequest;

@FeignClient(url="localhost:9010/user", name="user")
public interface UserClient {
	
	@GetMapping("/{pan}")
	public JwtRequest getUserByPan(@PathVariable("pan") String pan);

}
