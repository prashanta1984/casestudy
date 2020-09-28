package com.ibm.orderms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.orderms.entity.Orders;
import com.ibm.orderms.repository.OrdersRepository;
@RestController
@RequestMapping("/orderservice")
public class OrderController {
	
	private static Logger log = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	OrdersRepository orderrespoRepository;
	
	@PostMapping("/placeorder")
	@Transactional
	public void placeOrder(@RequestBody ArrayList<Orders> orderList,@RequestHeader("Authorization") String authHeaderr)
	{
		log.debug("inside place order Service");
		orderrespoRepository.saveAll(orderList);
		log.debug("order placed");
	}
	@GetMapping("/getorders")
	public List<Orders> getAllOrders() {
		return orderrespoRepository.findAll();
	}

}
