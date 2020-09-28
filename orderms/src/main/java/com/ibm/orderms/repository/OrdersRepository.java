package com.ibm.orderms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.orderms.entity.Orders;





public interface OrdersRepository extends JpaRepository<Orders, Long> {

	
}
