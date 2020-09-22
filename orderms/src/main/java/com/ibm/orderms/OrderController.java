package com.ibm.orderms;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/orderservice")
public class OrderController {
	@Autowired
	OrdersRepository orderrespoRepository;
	
	@PostMapping("/placeorder")
	@Transactional
	public void placeOrder(@RequestBody ArrayList<Orders> orderList)
	{
		orderrespoRepository.saveAll(orderList);
	}

}
