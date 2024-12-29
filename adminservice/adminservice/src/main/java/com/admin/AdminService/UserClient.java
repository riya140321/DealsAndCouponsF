package com.admin.AdminService;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.admin.Model.User;

import jakarta.validation.Valid;


@FeignClient(url="http://localhost:7189/user",name="User-Client")
public interface UserClient {
	
	@PostMapping("/add")
	public User addUser(@Valid @RequestBody User user);
	
	
	@GetMapping("/getAll")
	public List<User> getAllUser();
	
	
	@DeleteMapping("/deleteUser/{userId}")
	public String deleteUser(@PathVariable long userId);
	
	
	@PutMapping("/updateUser/{userId}")
	public User updateUser(@RequestBody User user,@PathVariable long userId);
	
	

}
