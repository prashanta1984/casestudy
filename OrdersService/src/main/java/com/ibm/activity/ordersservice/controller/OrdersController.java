package com.ibm.activity.ordersservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.activity.ordersservice.domain.ServiceStatus;
import com.ibm.activity.ordersservice.dto.OrdersDTO;
import com.ibm.activity.ordersservice.exception.ResourceCreationException;
import com.ibm.activity.ordersservice.exception.ResourceNotFoundException;
import com.ibm.activity.ordersservice.service.OrdersServiceImpl;

@RestController
@RequestMapping("/orderservice")
@CrossOrigin(origins="*")
public class OrdersController {
	@Autowired
	OrdersServiceImpl ordersService;

	@GetMapping("/check")
	
	public ServiceStatus orderServiceStatus() {
		ServiceStatus serStatus=new ServiceStatus();
		serStatus.setStatus("Service is up");
		return serStatus;
	}

	@PostMapping("/orders")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<OrdersDTO> createOrder(@RequestBody OrdersDTO orderDto,
			@RequestHeader("Authorization") String authHeader) throws ResourceCreationException {
		OrdersDTO createdOrder = ordersService.createOrder(orderDto, authHeader);
		return ResponseEntity.ok().body(createdOrder);
	}

	@GetMapping("/users/{userid}/orders")
	public ResponseEntity<OrdersDTO> findAllOrdersByAnUser(@PathVariable(value = "userid") Long userId,
			@RequestHeader("Authorization") String authHeader) throws ResourceNotFoundException {
		OrdersDTO userOrders = ordersService.findAllOrdersByAnUser(userId, authHeader);

		return ResponseEntity.ok().body(userOrders);
	}

	@GetMapping("/users/{userid}/orders/{orderid}")
	public ResponseEntity<OrdersDTO> getUserAndOrder(@PathVariable(value = "userid") Long userId,
			@PathVariable(value = "orderid") Long orderId, @RequestHeader("Authorization") String authHeader)
			throws ResourceNotFoundException {
		return new ResponseEntity<>(ordersService.getDetailsByUserIdAndOrderId(userId, orderId, authHeader),
				HttpStatus.OK);
	}

	@GetMapping
	public <T> ResponseEntity<T> getAllOrders() {
		return new ResponseEntity<>(ordersService.findAll(), HttpStatus.OK);
	}

}
