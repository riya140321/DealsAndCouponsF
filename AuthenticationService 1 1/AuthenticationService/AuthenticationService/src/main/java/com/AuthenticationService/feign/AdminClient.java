package com.AuthenticationService.feign;


import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.AuthenticationService.DTO.AuthDTO;



@FeignClient(url="http://localhost:7190/admin",name="Admin-Client")
public interface AdminClient {

	
	@PostMapping("/addAdmin")
	public AuthDTO addAdmin( @RequestBody AuthDTO admin);

	
}

