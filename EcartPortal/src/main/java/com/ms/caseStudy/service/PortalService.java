package com.ms.caseStudy.service;

import java.util.List;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.caseStudy.bean.AuditData;
import com.ms.caseStudy.bean.Cart;
import com.ms.caseStudy.bean.CartBean;
import com.ms.caseStudy.bean.OrderData;
import com.ms.caseStudy.bean.Product;

@Service
public class PortalService {

	@Autowired
	ProductServiceproxy productServiceproxy;
	
	@Autowired
	CartServiceproxy cartServiceproxy;
	
	@Autowired
	AuditServiceproxy auditServiceproxy;
	
	@Autowired
	OrderServiceproxy orderServiceproxy;
	
	public List<Product> findProducts(String token) {
		System.out.println("------ token  "+token);
		token = "Bearer "+token;
		return productServiceproxy.getProducts(token);
	}
	
	public List<Product> findProductsByIds(String ids,String token) {
		token = "Bearer "+token;
		return productServiceproxy.searchByProductIds(ids,token);
	}
	
	public Product findProductsById(int id,String token) {
		token = "Bearer "+token;
		return productServiceproxy.searchByProductId(id,token,MDC.get("correlationId"));
	}
	
	public void orderCart(int id,String token) {
		token = "Bearer "+token;
		cartServiceproxy.orderCart(id,token);
	}
	
	public void addToCart(CartBean bean,String token) {
		System.out.println("------ token  "+token);
		token = "Bearer "+token;
		cartServiceproxy.addToCart(bean,token,MDC.get("correlationId"));
		
	}
	
	public List<Cart> getCart(String user,String token) {
		token = "Bearer "+token;
		return cartServiceproxy.getCarts(user,token,MDC.get("correlationId"));
	}
	
	public List<AuditData> findLogs(String token) {
		System.out.println("------ token  "+token);
		token = "Bearer "+token;
		return auditServiceproxy.getAuditLogs(token);
	}
	
	public List<OrderData> findOrders(String token) {
		System.out.println("------ token  "+token);
		token = "Bearer "+token;
		return orderServiceproxy.getOrderList(token);
	}
	
}
