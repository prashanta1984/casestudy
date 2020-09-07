package com.ms.caseStudy.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ms.caseStudy.bean.OrderData;

@FeignClient(name = "OrderMS", fallback = OrderServiceFallback.class)
public interface OrderServiceproxy {
	
	@RequestMapping(value = "/orders/allOrders", method = RequestMethod.GET)
	public List<OrderData> getOrderList(@RequestHeader("Authorization") String token );
	
}
