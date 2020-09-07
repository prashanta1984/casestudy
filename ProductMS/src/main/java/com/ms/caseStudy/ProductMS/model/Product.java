package com.ms.caseStudy.ProductMS.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "product")
public class Product {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(name = "name", table = "product")
	private String name;
	@Column(name = "shortdescription", table = "product")
	private String shortDescription;
	@Column(name = "mrp", table = "product")
	private double mrp;

	public Product() {
		super();
	}

	public Product(String name, String shortDescription, double mrp) {
		super();
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
	
}	