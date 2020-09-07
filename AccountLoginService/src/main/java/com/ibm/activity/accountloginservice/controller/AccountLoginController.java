package com.ibm.activity.accountloginservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.activity.accountloginservice.dto.MyUserDetailsDTO;
import com.ibm.activity.accountloginservice.service.MyUserDetailsServiceImpl;

@RestController
@RequestMapping("/loginservice")
public class AccountLoginController {

	@Autowired
	MyUserDetailsServiceImpl myServiceUserData;

	@Value("Secured Login")
	private String message;

	@GetMapping("/")
	public String open() {
		return "You are general user";
	}

	@GetMapping("/msg")
	public String getMsg() {
		return this.message;
	}

	@PostMapping("/createuser")
	public ResponseEntity<MyUserDetailsDTO> insertNewUser(@RequestBody MyUserDetailsDTO details) {
		if (myServiceUserData.loadUserByUsername(details.getUsername()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} else {
			MyUserDetailsDTO registeredData = myServiceUserData.createNewUser(details);
			return new ResponseEntity<>(registeredData, HttpStatus.CREATED);
		}
	}

	@GetMapping("/getAlluser")
	public <T> ResponseEntity<T> userDetails() {
		return new ResponseEntity<>(myServiceUserData.findAll(), HttpStatus.OK) ;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<MyUserDetailsDTO> getUserDetails(@PathVariable(value = "id") Long id) {

		return ResponseEntity.ok().body(myServiceUserData.getUserById(id));

	}

}
