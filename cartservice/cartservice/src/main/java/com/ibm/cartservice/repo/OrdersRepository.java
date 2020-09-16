package com.ibm.cartservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.cartservice.bean.Orders;



public interface OrdersRepository extends JpaRepository<Orders, Long> {

	
}
