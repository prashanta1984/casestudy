package com.ibm.activity.ordersservice.service;

import com.ibm.activity.ordersservice.dto.OrdersDTO;
import com.ibm.activity.ordersservice.exception.ResourceCreationException;
import com.ibm.activity.ordersservice.exception.ResourceNotFoundException;

public interface IOrderService {

	public OrdersDTO createOrder(OrdersDTO orderDto, String authHeader) throws ResourceCreationException;

	public OrdersDTO getDetailsByUserIdAndOrderId(Long userId, Long orderId, String authHeader)
			throws ResourceNotFoundException;

	public OrdersDTO findAllOrdersByAnUser(Long userId, String authHeader) throws ResourceNotFoundException;
}
