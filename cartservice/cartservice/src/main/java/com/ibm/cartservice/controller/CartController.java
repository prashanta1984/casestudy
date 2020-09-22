package com.ibm.cartservice.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.integration.support.MessageBuilder;
import com.ibm.cartservice.audit.OrderServiceSource;
import com.ibm.cartservice.bean.CartBean;
import com.ibm.cartservice.bean.Orders;
import com.ibm.cartservice.bean.Product;
import com.ibm.cartservice.client.OrderFeignClient;
import com.ibm.cartservice.client.ProductFeignClient;
import com.ibm.cartservice.dto.OrdersDTO;
import com.ibm.cartservice.dto.ProductDTO;
import com.ibm.cartservice.model.Cart;
import com.ibm.cartservice.repo.CartRepository;
import com.ibm.cartservice.repo.OrdersRepository;



@RestController
@RequestMapping("/cart")
@EnableBinding(OrderServiceSource.class)
public class CartController {
	
	Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	CartRepository cartRepository;
	@Autowired
	ProductFeignClient productFeignClient;
	
	@Autowired
	OrderServiceSource orderServiceSource;
	@Autowired
	OrderFeignClient orderFeignClient;
	
	@Autowired
	OrdersRepository orderRepository;
	
	//private MessageChannel orderChannel;
	 
	/*
	 * public CartController(OrderBinding binding) { orderChannel =
	 * binding.orderChannel(); }
	 */
	@GetMapping("/check")
	public CartBean check()
	{
		CartBean cartBean= new CartBean();
		List<Product> productList= new ArrayList<Product>();
		productList.add(new Product(1L, "Table", "Dining", 500));
		cartBean.setProducts(productList);
		return cartBean;
	}

	@PostMapping("/cartAdd")
	@Transactional
	public void addCart(@RequestBody CartBean cartBean) {
		
		List<Cart> cartList = new ArrayList<>();
		Cart cart;
		for (Product product : cartBean.getProducts()) {
			ResponseEntity<ProductDTO> productDetailsById = productFeignClient.getProduct(product.getId());
			if(productDetailsById !=null)
			{
				cart = new Cart();
				cart.setUserName(cartBean.getUserName());
				cart.setProductName(product.getName());
				cart.setPrice(product.getMrp());
				cartList.add(cart);
			}
		}
		//orderServiceSource.orderChannel().send(MessageBuilder.withPayload(cartBean).build());
		cartRepository.saveAll(cartList);
		
		logger.debug("add to cart successful");
	}

	@GetMapping("/allCartList/{user}")
	public List<Cart> getAllCart(@PathVariable String user) {
		List<Cart> actual = cartRepository.findByUserName(user);
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
		/*
		 * Message<String> msg = MessageBuilder.withPayload(message.toString()).build();
		 * this.orderChannel.send(msg);
		 */
	    logger.debug("order submitted");
	}
	
	@PostMapping("/placeorder")
	@Transactional
	public void placeOrder(@RequestBody CartBean cartBean) {
		
		logger.debug("orderCart ::::  "+cartBean);
		StringBuilder message = new StringBuilder() ;
		List<Cart> cartitemDetails = cartRepository.findByUserName(cartBean.getUserName());
		Orders orders;
		
		ArrayList<Orders> orderList=new ArrayList<Orders>();
		for (Cart cart : cartitemDetails) {
			orders = new Orders();
			
			orders.setUserId(cart.getUserName());
			orders.setProductName(cart.getProductName());
			orders.setProductPrice(cart.getPrice());
			
			orderList.add(orders);
			
			
			cartRepository.deleteById(cart.getId());
		}
		
		//orderRepository.saveAll(orderList);
		orderFeignClient.placeOrder(orderList);
		
		
		
		orderServiceSource.orderChannel().send(MessageBuilder.withPayload(orderList).build());
	    logger.debug("order submitted");
	}

}
