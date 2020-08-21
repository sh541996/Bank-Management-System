package com.cts.bms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.bms.repository.AccountRepository;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class UserCreateAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCreateAccountServiceApplication.class, args);
	}

}
