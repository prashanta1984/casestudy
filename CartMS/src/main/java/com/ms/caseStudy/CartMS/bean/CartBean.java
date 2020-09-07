package com.ms.caseStudy.CartMS.bean;

import java.util.List;

public class CartBean {

	private String userName ;
	private List<Product> products ;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
}
