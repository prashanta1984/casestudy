package com.ms.audit.auditconms;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;

@SpringBootApplication
@EnableBinding(Sink.class)
public class AuditconmsApplication {
	private static Logger log = LoggerFactory.getLogger(AuditconmsApplication.class);
	@Autowired
	AuditEventRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(AuditconmsApplication.class, args);
	}
	
	@StreamListener(target = Sink.INPUT)
	public void processRegisterEmployees(@Payload String auditPayload) {
		log.info("Received =" + auditPayload);
		AuditEvent event = new AuditEvent(UUID.randomUUID().toString(), auditPayload,new Date());
		repo.save(event);
	}

}
