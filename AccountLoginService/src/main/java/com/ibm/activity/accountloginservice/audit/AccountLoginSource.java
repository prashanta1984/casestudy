package com.ibm.activity.accountloginservice.audit;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface AccountLoginSource {
	
	@Output("loginChannel")
    MessageChannel loginChannel();

}
