package com.ms.caseStudy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ms.caseStudy.bean.Cart;
import com.ms.caseStudy.bean.CartBean;
import com.ms.caseStudy.bean.Product;

@Component
public class CartServiceFallback implements CartServiceproxy {
	
	@Override
	public List<Product> orderCart(int id, String token) {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public List<Cart> getCarts(String user, String token, String coId) {
		return new ArrayList<Cart>();
	}

	@Override
	public void addToCart(CartBean cartBean, String token, String coId) {
		// TODO Auto-generated method stub
		
	}

}
