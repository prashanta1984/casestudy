package com.ibm.cartservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ibm.cartservice.model.Cart;



@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	/*
	 * @Query( value = "select * from cart c where c.user = ?1 " , nativeQuery =
	 * true) public List<Cart> getCartList(String name);
	 */
	  public List<Cart>  findByUserName(String name);
	
}
