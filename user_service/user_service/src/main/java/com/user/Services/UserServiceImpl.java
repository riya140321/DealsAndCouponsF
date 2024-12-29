package com.user.Services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.user.Entity.Coupon;
import com.user.Entity.User;
import com.user.Repository.UserRepository;
import com.user.exception.UserNotFoundException;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CouponClient couponClient;
	
	
	public User addUser( User user)
	{
     	return userRepository.save(user); 
		
	}
	
	public List<User> getAllUser()
	{
	     return userRepository.findAll();
	}
	
	public String deleteUser( long userId)
	{
		userRepository.deleteById(userId);
		return "Deleted Successfully";
	}
	
	public User updateUser( User user, long userId)
	{
		 User value= userRepository.findById(userId).orElseThrow(()->new RuntimeException());
		 value.setAge(user.getAge());
		 value.setEmail(user.getEmail());
		 value.setFirstName(user.getFirstName());
		 value.setGender(user.getGender());
		 value.setLastName(user.getLastName());
		 value.setPassword(user.getPassword());
		 value.setPhoneNumber(user.getPhoneNumber());
		 value.setUsername(user.getUsername());
		 return userRepository.save(value);
		
	}
	

	// coupon methods 
	
//	 public String addCoupon( Coupon coupon)
//	 {
//		 return couponClient.addCoupon(coupon);
//	 }
//	
	
	 public List<Coupon> getAllCoupons()
	 {
		 return couponClient.getAllCoupons();
	 }
	 
	
	 public Coupon getCouponById( long couponId)
	 {
		 return couponClient.getCouponById(couponId);
	 }
	
	
//	 public Coupon updateCoupon( Coupon coupon, long couponId)
//	 {
//		 return couponClient.updateCoupon(coupon, couponId);
//	 }
//	 
//	
//	 public String deleteCoupon( long couponId)
//	 {
//		 return couponClient.deleteCoupon(couponId);
//	 }
	 
	 public Coupon viewByProvider( String provider)
	 {
		 return couponClient.viewByProvider(provider);
	 }
	 
	 
	 public Coupon viewByCategory( String category)
	 {
		 return couponClient.viewByCategory(category);
	 }
	 
	
	 
}
;