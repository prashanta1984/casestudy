package com.ms.caseStudy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ms.caseStudy.bean.OrderData;

@Component
public class OrderServiceFallback implements OrderServiceproxy {

	@Override
	public List<OrderData> getOrderList(String token) {
		// TODO Auto-generated method stub
		return new ArrayList<OrderData>();
	}

	
	
}
