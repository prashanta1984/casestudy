package com.ms.caseStudy.bean;

public class Product {

	private Integer id;
	private String name;
	private String shortDescription;
	private double mrp;

	public Product() {
		super();
	}

	public Product(Integer id, String name, String shortDescription, double mrp) {
		super();
		this.id = id;
		this.name = name;
		this.shortDescription = shortDescription;
		this.mrp = mrp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", shortDescription=" + shortDescription + ", mrp=" + mrp + "]";
	}

}