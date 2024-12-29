package com.AuthenticationService.feign;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.AuthenticationService.DTO.AuthDTO;




@FeignClient(url="http://localhost:7189/user",name="User-Client")
public interface UserClient {
	
	@PostMapping("/add")
	public AuthDTO addUser( @RequestBody AuthDTO user);

}