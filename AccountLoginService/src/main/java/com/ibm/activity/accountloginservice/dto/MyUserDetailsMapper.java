package com.ibm.activity.accountloginservice.dto;

import com.ibm.activity.accountloginservice.domain.User;

public class MyUserDetailsMapper {

	public User convertMyUserDetailsDTOToUser(MyUserDetailsDTO detailsDto) {
		User user = new User();
		user.setUsername(detailsDto.getUsername());
		user.setPassword(detailsDto.getPassword());
		user.setEnabled(detailsDto.getEnabled());
		user.setRoles(detailsDto.getRoles());
		return user;
	}

	public MyUserDetailsDTO convertMyUserToMyUserDetailsDTO(User userEntity) {
		MyUserDetailsDTO userDetailsDto = new MyUserDetailsDTO();

		userDetailsDto.setUsername(userEntity.getUsername());
		userDetailsDto.setPassword(userEntity.getPassword());
		userDetailsDto.setEnabled(userEntity.getEnabled());
		userDetailsDto.setRoles(userEntity.getRoles());
		return userDetailsDto;
	}

}
