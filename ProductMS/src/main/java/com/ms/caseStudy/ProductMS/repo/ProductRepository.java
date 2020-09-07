package com.ms.caseStudy.ProductMS.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ms.caseStudy.ProductMS.model.Product;

@Component
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query( value = "select * from product c where c.id in ( ?1 )" , nativeQuery = true)
	public List<Product> getProductByIds(int ids[]);
	
}
