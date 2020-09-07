package com.ms.caseStudy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ms.caseStudy.bean.Product;

@Component
public class ProductServiceFallback implements ProductServiceproxy {

	
	@Override
	public List<Product> getProducts(String token) {
		System.out.println("fallback called");
		return new ArrayList<>();
	}

	@Override
	public List<Product> searchByProductIds(String ids,String token) {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public Product searchByProductId(int id, String token, String coId) {
		// TODO Auto-generated method stub
		return new Product();
	}

}
