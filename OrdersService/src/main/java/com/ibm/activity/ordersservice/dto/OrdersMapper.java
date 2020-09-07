package com.ibm.activity.ordersservice.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.activity.ordersservice.domain.Orders;

@Component
public class OrdersMapper {

	public Orders convertOrderDtoToOrder(OrdersDTO orderDto) {
		Orders order = new Orders();
		order.setOrderId(orderDto.getOrderId());
		order.setProductId(orderDto.getProductId());
		order.setUserId(orderDto.getUserId());
		return order;
	}

	public OrdersDTO convertOrderToOrderDto(Orders order) {
		OrdersDTO orderDto = new OrdersDTO();
		orderDto.setOrderId(order.getOrderId());
		orderDto.setProductId(order.getProductId());
		orderDto.setUserId(order.getUserId());
		return orderDto;
	}

	public OrdersDTO convertOrderListToOrderDtoList(List<Orders> listOrders) {
		OrdersDTO orderDto = new OrdersDTO();
		orderDto.setOrderList(listOrders);
		return orderDto;
	}

}
