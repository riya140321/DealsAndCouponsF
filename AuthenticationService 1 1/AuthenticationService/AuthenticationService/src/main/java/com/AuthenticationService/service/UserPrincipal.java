package com.AuthenticationService.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.AuthenticationService.model.User;

public class UserPrincipal implements UserDetails{

	private User user;
	public  UserPrincipal(User user) {
		this.user=user;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		return Collections.singleton(()->"ROLE_"+user.getRole());
	}

	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
	
		return user.getUsername();
	}

}
