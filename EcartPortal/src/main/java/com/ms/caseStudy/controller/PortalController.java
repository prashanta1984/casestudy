package com.ms.caseStudy.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.caseStudy.HelloBinding;
import com.ms.caseStudy.bean.AccessTokenBean;
import com.ms.caseStudy.bean.AuditData;
import com.ms.caseStudy.bean.Cart;
import com.ms.caseStudy.bean.CartBean;
import com.ms.caseStudy.bean.LoginBean;
import com.ms.caseStudy.bean.OrderData;
import com.ms.caseStudy.bean.Product;
import com.ms.caseStudy.service.PortalService;

@Controller
public class PortalController {

	//@Autowired
	//OAuth2RestTemplate template;

	@Autowired
	PortalService portalService;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	Logger logger = LoggerFactory.getLogger(PortalController.class);

	 private MessageChannel greet;
	 
	 public PortalController(HelloBinding binding) {
	        greet = binding.greeting();
	}
	
	/*
	 * @GetMapping("/backup") public ModelAndView gotoOauth2Restclient() {
	 * System.out.println("inside");
	 * 
	 * OAuth2AccessToken accessToken = template.getAccessToken();
	 * System.out.println(accessToken.getValue());
	 * System.out.println(accessToken.getRefreshToken());
	 * System.out.println(accessToken.getTokenType());
	 * System.out.println(accessToken.getExpiration()); return new
	 * ModelAndView("index", "loginBean", new LoginBean()); }
	 */

	@GetMapping("/home")
	public ModelAndView gotoHome() throws HttpException, IOException {
		return new ModelAndView("login", "loginBean", new LoginBean());
	}

	@PostMapping("/login")
	public ModelAndView securedResource(@ModelAttribute LoginBean loginBean, HttpSession session) {
		String message = null;
		String gotoPage = null;
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			List<ServiceInstance> instances = discoveryClient.getInstances("Oauth2server");
			ServiceInstance instance = instances.get(0);
			System.out.println("   --  "+loginBean);
			
			HttpClient client = new HttpClient();
			PostMethod postMethod = new PostMethod("http://" + "192.168.99.105" + ":" + instance.getPort() +"/oauth/token");
			postMethod.setRequestBody(
					"grant_type=password&username=" + loginBean.getUserName() + "&password=" + loginBean.getPassword());
			Header header = new Header("Content-type", "application/x-www-form-urlencoded");
			postMethod.setRequestHeader(header);

			String authString = "myclient" + ":" + "secret";
			String base64encodedString = Base64.getEncoder().encodeToString(authString.getBytes("utf-8"));

			Header header1 = new Header("Authorization", "Basic " + base64encodedString);
			postMethod.setRequestHeader(header1);
			int i = client.executeMethod(postMethod);
			System.out.println(i);
			System.out.println(postMethod.getResponseBodyAsString());

			if (i == 200) {
				gotoPage = "home";
				ObjectMapper mapper = new ObjectMapper();
				AccessTokenBean token = mapper.readValue(postMethod.getResponseBodyAsString(), AccessTokenBean.class);
				message = "Successful logged in with Token : " + token.getAccess_token();
				session.setAttribute("user", loginBean.getUserName());
				session.setAttribute("acctoken", token.getAccess_token());
				System.out.println("calling cart");
				
				List<Cart> carts = portalService.getCart(loginBean.getUserName(),token.getAccess_token());
				modelAndView.addObject("carts", carts);
				
				System.out.println("call Audit logs");
				List<AuditData> auditLogs = portalService.findLogs(token.getAccess_token());
				System.out.println("logs " + auditLogs.size());
				session.setAttribute("auditLogs", auditLogs);
				
			} else {
				gotoPage = "login";
				message = "Bad Credentials";
				Message<String> msg = MessageBuilder.withPayload(loginBean.getUserName() +"|"+loginBean.getPassword())
			            .build();
			        this.greet.send(msg);
			}

			modelAndView.setViewName(gotoPage);
			modelAndView.addObject("status", message);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;
	}

	@GetMapping("/findProduts")
	public ModelAndView findProducts(HttpSession httpSession) {
		logger.debug("call products");
		List<Product> products = portalService.findProducts(httpSession.getAttribute("acctoken").toString());
		logger.debug("products " + products.size());
		httpSession.setAttribute("products", products);
		return new ModelAndView("home", "products", products);
	}

	@PostMapping("/addCart")
	public ModelAndView addToCarts(@RequestParam(value = "pids", required = false) String[] pids,
			HttpSession httpSession) {
		Arrays.stream(pids).forEach(System.out::println);
		List<Cart> carts = null ;
		List<Product> products = new ArrayList<Product>();
		ModelAndView modelAndView = new ModelAndView("home");
		if(pids.length > 0) {
			for(String i : pids) {
				products.add(portalService.findProductsById(Integer.parseInt(i),httpSession.getAttribute("acctoken").toString()));
				logger.debug("find products by id "+i);
			}
			CartBean bean = new CartBean();
			bean.setUserName(httpSession.getAttribute("user").toString());
			bean.setProducts(products);
			portalService.addToCart(bean,httpSession.getAttribute("acctoken").toString());
			logger.debug("added to cart");
			logger.debug("user "+httpSession.getAttribute("user").toString());
			carts = portalService.getCart(httpSession.getAttribute("user").toString(),httpSession.getAttribute("acctoken").toString());
			logger.debug(" fetch carts "+carts.size());
		}
		
		products = portalService.findProducts(httpSession.getAttribute("acctoken").toString());
		modelAndView.addObject("products", httpSession.getAttribute("products"));
		modelAndView.addObject("carts", carts);

		return modelAndView;
	}
	
	@PostMapping("/orderCart")
	public ModelAndView orderCart(@RequestParam(value = "cids", required = false) String[] cids,
			HttpSession httpSession) {
		Arrays.stream(cids).forEach(System.out::println);
		ModelAndView modelAndView = new ModelAndView("home");
		if(cids.length > 0) {
			for(String i : cids) {
				portalService.orderCart(Integer.parseInt(i),httpSession.getAttribute("acctoken").toString());
				System.out.println("order cart done" + i);
			}
		}
		
		List<Cart> carts = portalService.getCart(httpSession.getAttribute("user").toString(),httpSession.getAttribute("acctoken").toString());
		modelAndView.addObject("products", httpSession.getAttribute("products"));
		modelAndView.addObject("carts", carts);
		
		/*
		 * System.out.println("call get order "); List<OrderData> orderList =
		 * portalService.findOrders(httpSession.getAttribute("acctoken").toString());
		 * System.out.println("orderList " + orderList.size());
		 * httpSession.setAttribute("orderList", orderList);
		 * modelAndView.addObject("orderList",orderList);
		 */
		return modelAndView;
	}

	
	 @GetMapping("/findLogs") public ModelAndView findLogs(HttpSession
			 httpSession) { System.out.println("call Audit logs"); List<AuditData>
	  auditLogs =
	  portalService.findLogs(httpSession.getAttribute("acctoken").toString());
	  System.out.println("logs " + auditLogs.size());
	 httpSession.setAttribute("auditLogs", auditLogs); return new
	  ModelAndView("home", "auditLogs", auditLogs); 
	 }
	 
	
	@GetMapping("/orderList")
	public ModelAndView getOrders(HttpSession httpSession) {
		System.out.println("call get order ");
		List<OrderData> orderList = portalService.findOrders(httpSession.getAttribute("acctoken").toString());
		System.out.println("orderList " + orderList.size());
		httpSession.setAttribute("orderList", orderList);
		
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("orderList",orderList);
		
		List<Cart> carts = portalService.getCart(httpSession.getAttribute("user").toString(),httpSession.getAttribute("acctoken").toString());
		System.out.println("carts "+carts.size());
		modelAndView.addObject("carts", carts);
		return modelAndView;
	}
}
