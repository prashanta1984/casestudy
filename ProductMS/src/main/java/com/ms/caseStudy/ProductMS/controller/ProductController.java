package com.ms.caseStudy.ProductMS.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.caseStudy.ProductMS.model.Product;
import com.ms.caseStudy.ProductMS.repo.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductRepository productRepository ; 
	
	@GetMapping("/allproducts")
	public List<Product> getAllProducts() {
		List<Product> products = productRepository.findAll();
		logger.debug(" allproducts : size "+products.size());
		return productRepository.findAll();
	}
	
	@GetMapping("/productByIds/{ids}")
	public List<Product> searchByIds(@PathVariable int ids[]) {
		List<Product> products = productRepository.getProductByIds(ids);
		if(products == null) {
			products = new ArrayList<Product>();
		}
		logger.debug("size1 "+products.size());
		return products;
	}
	
	@GetMapping("/productById/{id}")
	public Product searchById(@PathVariable int id) {
		Optional<Product> product = productRepository.findById(id);
		
		logger.debug(" productById size1 "+product.isPresent()+ "for Id : "+id);
		return product.get();
	}

}
