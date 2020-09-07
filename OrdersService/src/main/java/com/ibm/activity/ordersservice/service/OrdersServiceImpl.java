package com.ibm.activity.ordersservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ibm.activity.ordersservice.client.ProductFeignClient;
import com.ibm.activity.ordersservice.client.UsersFeignClient;
import com.ibm.activity.ordersservice.domain.Orders;
import com.ibm.activity.ordersservice.dto.MyUserDetailsDTO;
import com.ibm.activity.ordersservice.dto.OrdersDTO;
import com.ibm.activity.ordersservice.dto.OrdersMapper;
import com.ibm.activity.ordersservice.dto.ProductDTO;
import com.ibm.activity.ordersservice.exception.ResourceCreationException;
import com.ibm.activity.ordersservice.exception.ResourceNotFoundException;
import com.ibm.activity.ordersservice.repository.OrdersRepository;

@Service
public class OrdersServiceImpl implements IOrderService {

	@Autowired
	OrdersRepository ordersRepository;

	@Autowired
	ProductFeignClient productFeignClient;

	@Autowired
	UsersFeignClient usersFeignClient;

	@Autowired
	OrdersMapper mapper;

	public OrdersDTO createOrder(OrdersDTO orderDto, String authHeader) throws ResourceCreationException {

		ResponseEntity<ProductDTO> productDetailsById = productFeignClient.getProduct(orderDto.getProductId());
		ResponseEntity<MyUserDetailsDTO> userDetailsById = usersFeignClient.getUserDetails(orderDto.getUserId(),
				authHeader);

		if (productDetailsById.getBody().getId() != null && userDetailsById.getBody().getUsername() != null) {
			Orders order = ordersRepository.save(mapper.convertOrderDtoToOrder(orderDto));
			return mapper.convertOrderToOrderDto(order);
		} else {
			throw new ResourceCreationException(
					"Product:" + orderDto.getProductId() + "::User:" + orderDto.getUserId());
		}
	}

	@Override
	public OrdersDTO getDetailsByUserIdAndOrderId(Long userId, Long orderId, String authHeader)
			throws ResourceNotFoundException {

		ResponseEntity<MyUserDetailsDTO> details = usersFeignClient.getUserDetails(userId, authHeader);
		Optional<Orders> orderEntity = ordersRepository.findById(orderId);
		OrdersDTO orderDto = new OrdersDTO();

		orderEntity.ifPresent(oe -> {
			if (oe.getUserId() == userId) {
				orderDto.setOrderId(oe.getOrderId());
				orderDto.setProductId(oe.getProductId());
				orderDto.setUserName(details.getBody().getUsername());
			}
		});

		if (orderDto.getOrderId() != null) {
			return orderDto;
		} else {
			throw new ResourceNotFoundException(
					"orderId And userId is missing: OrderId:" + orderId + ":UserId:" + userId);
		}
	}

	public OrdersDTO findAllOrdersByAnUser(Long userId, String authHeader) throws ResourceNotFoundException {

		ResponseEntity<MyUserDetailsDTO> userDetails = usersFeignClient.getUserDetails(userId, authHeader);

		if (userDetails.getBody().getUsername() != null) {
			List<Orders> listOrdersMapper = ordersRepository.findByUserId(userId);
			return mapper.convertOrderListToOrderDtoList(listOrdersMapper);
		} else {
			throw new ResourceNotFoundException(userId + "");
		}

	}

	@SuppressWarnings("rawtypes")
	public List findAll() {
		return ordersRepository.findAll();
	}

}
