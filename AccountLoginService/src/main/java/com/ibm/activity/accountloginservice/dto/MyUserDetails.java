package com.ibm.activity.accountloginservice.dto;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ibm.activity.accountloginservice.domain.User;

public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 7749773360482979061L;
	private String username;
	private String password;
	private Boolean enabled;
	private List<GrantedAuthority> authorities;

	public MyUserDetails(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.enabled = user.getEnabled();
		this.authorities = Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return username;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return enabled;
	}

}
