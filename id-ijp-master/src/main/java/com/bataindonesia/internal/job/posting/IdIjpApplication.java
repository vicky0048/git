package com.bataindonesia.internal.job.posting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IdIjpApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdIjpApplication.class, args);
	}
	
//	@Bean
//	@LoadBalanced
//	public RestTemplate getRestTemplate() {
//		return new RestTemplate();
//	}

}
