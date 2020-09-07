package com.ms.caseStudy.ProductMS;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.ms.caseStudy.ProductMS.model.Product;
import com.ms.caseStudy.ProductMS.repo.ProductRepository;

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
public class ProductMsApplication {

	@Autowired
	ProductRepository productRepository ;
	
	public static void main(String[] args) {
		SpringApplication.run(ProductMsApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			populateData();
		};
	}

	@Transactional
	public void populateData() {
		Product p = new Product("Television", "70 CM Android LED TV", 50000);
		productRepository.save(p);
		p = new Product("iPhone", "Apple iPhone 11 Pro Max 256GB", 131600);
		productRepository.save(p);
		p = new Product("Laptop", "HP Pavilion Core i7 9th Gen", 95000);
		productRepository.save(p);
		p = new Product("Camera", "Nikon Z 50 Mirrorless Camera", 90990);
		productRepository.save(p);
		
	}
}
