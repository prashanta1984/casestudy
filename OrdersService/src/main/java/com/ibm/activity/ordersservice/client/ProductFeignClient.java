package com.ibm.activity.ordersservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ibm.activity.ordersservice.dto.ProductDTO;

@FeignClient("product-service")
public interface ProductFeignClient {

	@GetMapping("product/productlist/")
	public ResponseEntity<ProductDTO> productDetails();

	@GetMapping("product/{id}/")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable(value = "id") Long id);

}
