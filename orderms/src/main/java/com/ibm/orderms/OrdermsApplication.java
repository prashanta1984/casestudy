package com.ibm.orderms;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;





@EnableHystrixDashboard
@EnableHystrix
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableBinding(Sink.class)
public class OrdermsApplication {
	
	private static Logger log = LoggerFactory.getLogger(OrdermsApplication.class);
	//@Autowired
	//OrdersRepository ordersRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrdermsApplication.class, args);
	}
	@StreamListener(target = Sink.INPUT)
	public void processRegisterEmployees(@Payload String orderPayload) {
		log.info("Order Payload Received =" + orderPayload);
		//Orders orders=new Orders();
		//System.out.println(orderPayload);
		//AuditEvent event = new AuditEvent(UUID.randomUUID().toString(), auditPayload,new Date());
		//repo.save(event);
	}
}
