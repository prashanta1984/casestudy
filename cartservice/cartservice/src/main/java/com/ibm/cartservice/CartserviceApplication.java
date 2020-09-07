package com.ibm.cartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableHystrixDashboard
@EnableHystrix
@EnableFeignClients
@EnableDiscoveryClient

public class CartserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartserviceApplication.class, args);
	}

}