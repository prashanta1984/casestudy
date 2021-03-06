package com.ms.caseStudy.CartMS.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.caseStudy.CartMS.OrderBinding;
import com.ms.caseStudy.CartMS.bean.CartBean;
import com.ms.caseStudy.CartMS.bean.Product;
import com.ms.caseStudy.CartMS.model.Cart;
import com.ms.caseStudy.CartMS.repo.CartRepository;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	CartRepository cartRepository;
	
	private MessageChannel orderChannel;
	 
	 public CartController(OrderBinding binding) {
		 orderChannel = binding.orderChannel();
	}

	@PostMapping("/cartAdd")
	@Transactional
	public void addCart(@RequestBody CartBean cartBean) {
		List<Cart> cartList = new ArrayList<>();
		Cart cart;
		for (Product product : cartBean.getProducts()) {
			cart = new Cart();
			cart.setUserName(cartBean.getUserName());
			cart.setProductName(product.getName());
			cart.setPrice(product.getMrp());
			cartList.add(cart);
		}
		cartRepository.saveAll(cartList);
		logger.debug("add to cart successful");
	}

	@GetMapping("/allCartList/{user}")
	public List<Cart> getAllCart(@PathVariable String user) {
		List<Cart> actual = cartRepository.getCartList(user);
		for(Cart cart : actual) {
			logger.debug("Cart object "+cart.toString());
		}
		logger.debug("Cart objects size"+actual.size());
		return actual;
	}
	
	@PostMapping("/orderCart")
	public void orderCart(@RequestBody int cartId) {
		logger.debug("orderCart ::::  "+cartId);
		StringBuilder message = new StringBuilder() ;
		Optional<Cart> cartBean = cartRepository.findById(cartId);
		cartRepository.deleteById(cartId);
		Cart cart = cartBean.get();
		message.append(cart.getProductName()).append("|").append(cart.getPrice());
		logger.debug("message : "+message);
		Message<String> msg = MessageBuilder.withPayload(message.toString()).build();
	    this.orderChannel.send(msg);
	    logger.debug("order submitted");
	}

}
