package com.ibm.activity.ordersservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.activity.ordersservice.domain.Orders;
import com.ibm.activity.ordersservice.exception.ResourceNotFoundException;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

	List<Orders> findByUserId(Long userId) throws ResourceNotFoundException;

}
