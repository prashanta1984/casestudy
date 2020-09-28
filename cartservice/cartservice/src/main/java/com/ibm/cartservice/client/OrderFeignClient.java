package com.ibm.cartservice.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ibm.cartservice.bean.Orders;
import com.ibm.cartservice.dto.OrdersDTO;

@FeignClient("orderms")
public interface OrderFeignClient {
	@PostMapping("/orderservice/placeorder")
	public void placeOrder(@RequestBody ArrayList<Orders> list,@RequestHeader("Authorization") String authHeaderr,@RequestHeader("X-Correlation-Id") String coId);
}
