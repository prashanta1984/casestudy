package com.ms.caseStudy.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ms.caseStudy.bean.Product;

@FeignClient(name = "ProductMS", fallback = ProductServiceFallback.class)
public interface ProductServiceproxy {
	
	@RequestMapping(value = "/products/allproducts", method = RequestMethod.GET)
	public List<Product> getProducts(@RequestHeader("Authorization") String token);
	
	@RequestMapping(value = "/products/productById/{ids}", method = RequestMethod.GET)
	public List<Product> searchByProductIds(@PathVariable String ids ,@RequestHeader("Authorization") String token);

	@RequestMapping(value = "/products/productById/{id}", method = RequestMethod.GET)
	public Product searchByProductId(@PathVariable int id ,@RequestHeader("Authorization") String token,@RequestHeader("X-Correlation-Id") String coId);
}
