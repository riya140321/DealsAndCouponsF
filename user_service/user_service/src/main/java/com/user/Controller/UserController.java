package com.user.Controller;

import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.Entity.Coupon;
import com.user.Entity.User;
import com.user.Services.UserService;
import com.user.Services.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	
	@Autowired
	private UserService userService;
	
	
	//user methods  
	
	@PostMapping("/add")
	@CircuitBreaker(name="userService" , fallbackMethod="addUserFallback")
	public ResponseEntity<User> addUser( @RequestBody User user)
	{
		User savedUser = userServiceImpl.addUser(user);
		return ResponseEntity.ok(savedUser); 
	}
	
	public User addUserFallback(User user,Throwable throwable)
	{
		System.out.println("Error occurred in addUser:" +throwable.getMessage());

		User addUserFallback=new User();
		addUserFallback.setAge(null);
		addUserFallback.setEmail("Dummmy");
		addUserFallback.setFirstName("DummyName");
		addUserFallback.setGender("Error");
		addUserFallback.setLastName("Dummmy2");
		addUserFallback.setPassword("*****");
		addUserFallback.setPhoneNumber("100000");
		addUserFallback.setUserId(0L);
		addUserFallback.setUsername("Not-Available");
        
        return addUserFallback;
	}
	
	@GetMapping("/getAll")
	@CircuitBreaker(name = "userService", fallbackMethod = "getAllUserFallback")
	public List<User> getAllUser()
	{
		return userServiceImpl.getAllUser();
	}
	
	public List<User> getAllUserFallback(Throwable throwable) {
        System.out.println("Error occurred in getAllUser: " + throwable.getMessage());
        
        User getAllUserFallback = User.builder()
        		.age("Error")
        		.email("Unknown")
        		.firstName("Dummy")
        		.gender("NOt Given")
        		.lastName("Dummy")
        		.password("****")
        		.phoneNumber("11111")
        		.userId(0L)
        		.username("Null")
        		.build();
        
        return List.of(getAllUserFallback);
    }
	
	@DeleteMapping("/deleteUser/{userId}")
	public String deleteUser(@PathVariable long userId)
	{
		return userServiceImpl.deleteUser(userId);
	}
	
	@PutMapping("/updateUser/{userId}")
	public User updateUser(@RequestBody User user,@PathVariable long userId)
	{
		return userServiceImpl.updateUser(user, userId);
	}
	
	//coupon methods 
	
//	@PostMapping("/addCoupon")
//	 @CircuitBreaker(name = "couponService", fallbackMethod = "addCouponFallback")
//	 public String addCoupon(@Valid @RequestBody Coupon coupon)
//	 {
//		return userService.addCoupon(coupon);
//	 }
//	
//	public String addCouponFallback(Coupon coupon, Throwable throwable)
//	{
//        System.out.println("Error occurred in addCoupon: " + throwable.getMessage());
//        return "Error occurred while adding coupon. Please try again later.";
//    }
	
	 
	 @GetMapping("/getAllCoupons")
	 @CircuitBreaker(name = "couponService", fallbackMethod = "getAllCouponsFallback")
	 public List<Coupon> getAllCoupons()
	 {
		 return userService.getAllCoupons();
	 }
	 
	 public List<Coupon> getAllCouponsFallback(Throwable throwable) {
	        System.out.println("Error occurred in getAllCoupons: " + throwable.getMessage());
//	       Coupon c= Coupon.builder().category("check").build();
//	       List<Coupon> list=new ArrayList<Coupon>();
//	       list.add(c);
//	       return list;
	        
	        Coupon getAllCouponsFallback = Coupon.builder()
	        		.category("Clothes")
	        		.couponCode("Dummy")
	        		.description("GetAllCoupons")
	        		.expiryDate("12-11-2024")
	        		.provider("Myntra")
	        		.couponId(0L)
	        		.build();
	        
	        return List.of(getAllCouponsFallback);
	    }
	 
	 
	 @GetMapping("/getById/{couponId}")
	 @CircuitBreaker(name = "couponService", fallbackMethod = "getCouponByIdFallback")
	 public Coupon getCouponById(@PathVariable long couponId)
	 {
		 return userService.getCouponById(couponId);
	 }
	 
	 public Coupon getCouponByIdFallback(long couponId, Throwable throwable) 
	 {
	        System.out.println("Error occurred in getCouponById: " + throwable.getMessage());
	        
	        Coupon getCouponByIdFallback=new Coupon();
	        getCouponByIdFallback.setCategory("Error Category");
	        getCouponByIdFallback.setCouponCode("Unknown");
	        getCouponByIdFallback.setDescription("Not Available ");
	        getCouponByIdFallback.setExpiryDate("Unknown");
	        getCouponByIdFallback.setProvider("Errorr!!");
	        getCouponByIdFallback.setCouponId(0L);
	        
	        return getCouponByIdFallback;
	        
	      
	    }
	
	 
//	 @PutMapping("/updateCoupon/{couponId}")
//	 public Coupon updateCoupon(@RequestBody Coupon coupon,@PathVariable long couponId)
//	 {
//		 return userService.updateCoupon(coupon, couponId);
//	 }
//	 
//	 @DeleteMapping("/deleteCoupon/{couponId}")
//	 public String deleteCoupon(@PathVariable long couponId)
//	 {
//		 return userService.deleteCoupon(couponId);
//	 }
	 
	 
	 @GetMapping("/getByName/{provider}")
	 @CircuitBreaker(name = "couponService", fallbackMethod = "viewByProviderFallback")
	 public Coupon viewByProvider(@PathVariable String provider)
	 {
		 return userService.viewByProvider(provider);
	 }
	 
	 public Coupon viewByProviderFallback(String provider, Throwable throwable) {
	        System.out.println("Error occurred in viewByProvider: " + throwable.getMessage());
	        
	        Coupon viewByProviderFallback=new Coupon();
	        viewByProviderFallback.setCategory("Error Category");
	        viewByProviderFallback.setCouponCode("Unknown");
	        viewByProviderFallback.setDescription("Not Available ");
	        viewByProviderFallback.setExpiryDate("Unknown");
	        viewByProviderFallback.setProvider("Errorr!!");
	        viewByProviderFallback.setCouponId(0L);
	        
	        return viewByProviderFallback;
	        
	        
	    }
	 
	 
	 @GetMapping("/getByCategory/{category}")
	 @CircuitBreaker(name = "couponService", fallbackMethod = "viewByCategoryFallback")
	 public Coupon viewByCategory(@PathVariable String category)
	 {
		 return userService.viewByCategory(category);
	 }
	 
	 public Coupon viewByCategoryFallback(String category, Throwable throwable) {
	        System.out.println("Error occurred in viewByCategory: " + throwable.getMessage());
	        
	        Coupon viewByCategoryFallback=new Coupon();
	        viewByCategoryFallback.setCategory("Error Category");
	        viewByCategoryFallback.setCouponCode("Unknown");
	        viewByCategoryFallback.setDescription("Not Available ");
	        viewByCategoryFallback.setExpiryDate("Unknown");
	        viewByCategoryFallback.setProvider("Errorr!!");
	        viewByCategoryFallback.setCouponId(1L);
	        
	        return viewByCategoryFallback;
	    }
	 
	 
	 
}
