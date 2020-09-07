package com.ms.caseStudy.bean;

public class OrderData {
	
	private Integer id;
	private String orderId;
	private String productName;
	private String productPrice;
	private String deliveryDate;

	

	public OrderData(Integer id, String orderId, String productName, String productPrice, String deliveryDate) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.deliveryDate = deliveryDate;
	}

	public OrderData() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}	