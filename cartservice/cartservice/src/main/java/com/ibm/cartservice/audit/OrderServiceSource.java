package com.ibm.cartservice.audit;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OrderServiceSource {
	
	@Output("orderChannel")
    MessageChannel orderChannel();

}
