package com.cts.bms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class UserInvestmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserInvestmentServiceApplication.class, args);
	}

}
