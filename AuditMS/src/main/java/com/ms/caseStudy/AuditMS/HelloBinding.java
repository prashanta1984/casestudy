package com.ms.caseStudy.AuditMS;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

interface HelloBinding {
	
	String GREETING = "greetingChannel";

	 @Input(GREETING)
	 SubscribableChannel greeting();

}