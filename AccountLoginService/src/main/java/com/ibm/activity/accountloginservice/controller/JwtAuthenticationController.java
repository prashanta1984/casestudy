package com.ibm.activity.accountloginservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.activity.accountloginservice.audit.AccountLoginSource;
import com.ibm.activity.accountloginservice.dto.AuthenticationRequest;
import com.ibm.activity.accountloginservice.dto.AuthenticationResponse;
import com.ibm.activity.accountloginservice.service.MyUserDetailsServiceImpl;
import com.ibm.activity.accountloginservice.util.JwtUtil;




@RestController
@EnableBinding(AccountLoginSource.class)
public class JwtAuthenticationController {
	
	Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsServiceImpl userDetailsService;
	
	@Autowired
	AccountLoginSource aacountLoginSource;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			aacountLoginSource.loginChannel().send(MessageBuilder.withPayload(authenticationRequest.getUsername() +"|"+authenticationRequest.getPassword()).build());
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}
