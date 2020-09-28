package com.ibm.cartservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ibm.cartservice.dto.MyUserDetailsDTO;



@FeignClient("account-login-service")
public interface UsersFeignClient {

	@GetMapping("/loginservice/{id}")
	ResponseEntity<MyUserDetailsDTO> getUserDetails(@PathVariable(value = "id") Long id,
			@RequestHeader("Authorization") String authorization);
}
