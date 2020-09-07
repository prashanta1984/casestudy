package com.ms.caseStudy.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ms.caseStudy.bean.Cart;
import com.ms.caseStudy.bean.CartBean;
import com.ms.caseStudy.bean.Product;

@FeignClient(name = "CartMS", fallback = CartServiceFallback.class)
public interface CartServiceproxy {
	
	@RequestMapping(value = "/cart/allCartList/{user}", method = RequestMethod.GET)
	public List<Cart> getCarts(@PathVariable String user,@RequestHeader("Authorization") String token,@RequestHeader("X-Correlation-Id") String coId );
	
	@RequestMapping(value = "/cart/cartAdd", method = RequestMethod.POST)
	public void addToCart(@RequestBody CartBean cartBean,@RequestHeader("Authorization") String token,@RequestHeader("X-Correlation-Id") String coId);
	
	@RequestMapping(value = "/cart/orderCart", method = RequestMethod.POST)
	public List<Product> orderCart(@RequestBody int id,@RequestHeader("Authorization") String token);

}
