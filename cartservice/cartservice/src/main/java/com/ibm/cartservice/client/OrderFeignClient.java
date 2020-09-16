package com.ibm.cartservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import com.ibm.cartservice.dto.OrdersDTO;

@FeignClient("orders-service")
public interface OrderFeignClient {
	@PostMapping("/orderservice/orders")
	ResponseEntity<OrdersDTO> placeOrder(@RequestBody OrdersDTO orderDto,
			@RequestHeader("Authorization") String authHeader);
}
