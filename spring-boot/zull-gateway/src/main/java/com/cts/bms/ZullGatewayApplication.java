package com.cts.bms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cts.bms.jwt.model.JwtRequest;

@EnableZuulProxy
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class ZullGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZullGatewayApplication.class, args);
	}

	@Bean
	public JwtRequest jwtRequest( ) {
		return new JwtRequest();
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			
			@Override
			public void addCorsMappings(CorsRegistry registery) {
				registery.addMapping("/user/**").allowedMethods("GET","POST","PUT","DELETE").allowedHeaders("*").allowedOrigins("*")
				.allowCredentials(true);
				
				registery.addMapping("/account/**").allowedMethods("GET","POST","PUT","DELETE").allowedHeaders("*").allowedOrigins("*")
				.allowCredentials(true);
				
				registery.addMapping("/investment/**").allowedMethods("GET","POST","PUT","DELETE").allowedHeaders("*").allowedOrigins("*")
				.allowCredentials(true);
			}
		};
	}

}

