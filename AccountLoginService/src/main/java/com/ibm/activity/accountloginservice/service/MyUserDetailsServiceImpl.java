package com.ibm.activity.accountloginservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.activity.accountloginservice.domain.User;
import com.ibm.activity.accountloginservice.dto.MyUserDetails;
import com.ibm.activity.accountloginservice.dto.MyUserDetailsDTO;
import com.ibm.activity.accountloginservice.dto.MyUserDetailsMapper;
import com.ibm.activity.accountloginservice.repository.UserRepository;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

	private static final String ROLE_USER = "User";

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Used to return Details of User to convert user details in signed JWT token
	 * Used to Validate existing user for create user process.
	 */

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(userName);
		if (user.isPresent()) {
			return user.map(MyUserDetails::new).get();
		} else {
			return null;
		}

	}

	/**
	 * Create a user with bcrypt password and default role as 'User'
	 */
	public MyUserDetailsDTO createNewUser(MyUserDetailsDTO details) {
		MyUserDetailsMapper mapper = new MyUserDetailsMapper();
		details.setPassword(passwordEncoder.encode(details.getPassword()));
		details.setRoles(ROLE_USER);
		User convertMapperToEntity = mapper.convertMyUserDetailsDTOToUser(details);
		return mapper.convertMyUserToMyUserDetailsDTO(userRepository.save(convertMapperToEntity));
	}

	@SuppressWarnings("rawtypes")
	public List findAll() {
		return userRepository.findAll();
	}

	public MyUserDetailsDTO getUserById(Long id) {
		Optional<User> User = userRepository.findById(id);

		MyUserDetailsDTO userDetailsDTO = new MyUserDetailsDTO();
		if (User.isPresent()) {
			MyUserDetailsMapper mapper = new MyUserDetailsMapper();
			userDetailsDTO = mapper.convertMyUserToMyUserDetailsDTO(User.get());
		}
		return userDetailsDTO;
	}

}
