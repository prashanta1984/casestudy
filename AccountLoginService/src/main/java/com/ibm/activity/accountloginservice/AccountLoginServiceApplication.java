package com.ibm.activity.accountloginservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AccountLoginServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountLoginServiceApplication.class, args);
	}

}
