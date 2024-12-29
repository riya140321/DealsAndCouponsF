package com.admin.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.AdminService.AdminService;
import com.admin.AdminService.AdminServiceImpl;
import com.admin.Model.Admin;
import com.admin.Model.Coupon;
import com.admin.Model.User;
import com.admin.Repository.AdminRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;




@RestController
@RequestMapping("/admin")
public class AdminController {

	
	@Autowired
	 private AdminService adminService;
	
	 
	
	@PostMapping("/addAdmin")
	@CircuitBreaker(name="adminService" ,fallbackMethod="addAdminFallback")
	public Admin addAdmin( @RequestBody Admin admin)
	{
		return adminService.addAdmin(admin);
	}
	
	public Admin addAdminFallback(Admin admin,Throwable throwable)
	{
		System.out.println("Error occured in addAdmin :" +throwable.getMessage());
		Admin addAdminFallback = new Admin();
		addAdminFallback.setAdminId(0L);
		addAdminFallback.setAdminName("Error !!");
		addAdminFallback.setEmail("xyz@@");
		addAdminFallback.setPassword("*****");
		addAdminFallback.setUsername("Unknown");
		return addAdminFallback;
	}
	
	
	
	@GetMapping("/getAllAdmin")
	@CircuitBreaker(name="adminService" , fallbackMethod = "getAllAdminFallBack")
	public List<Admin> getAll()
	{
		return adminService.getAll();
	}
	
	public List<Admin> getAllAdminFallBack(Throwable throwable)
	{
		System.out.println("Error occured in getAllAdmin:"+throwable.getMessage());
		Admin getAllAdminFallBack= Admin.builder()
				.adminId(0L)
				.adminName("xyz")
				.email("xyz@.com")
				.password("1234")
				.username("Dummy")
				.build();
		
		return List.of(getAllAdminFallBack);
				
	}
	
	
	@DeleteMapping("/deleteAdmin/{adminId}")
	public String deleteAdmin(@PathVariable long adminId)
	{
		return adminService.deleteAdmin(adminId);
	}
	
	@PutMapping("/updateAdmin/{adminId}")
	public Admin updateAdmin(@RequestBody Admin admin,@PathVariable long adminId)
	{
		return adminService.updateAdmin(admin, adminId);
	}
	
	
	//coupon methods 
	
	@PostMapping("/addCoupon")
	@CircuitBreaker(name = "couponService", fallbackMethod = "addCouponFallback")
	 public String addCoupon(@Valid @RequestBody Coupon coupon)
	 {
		return adminService.addCoupon(coupon);
	 }
	
	public String addCouponFallback(Coupon coupon, Throwable throwable)
	{
        System.out.println("Error occurred in addCoupon: " + throwable.getMessage());
        return "Error occurred while adding coupon. Please try again later.";
    }
	
	 
	 @GetMapping("/getAllCoupons")
	 @CircuitBreaker(name = "couponService", fallbackMethod = "getAllCouponsFallback")
	 public List<Coupon> getAllCoupons()
	 {
		 return adminService.getAllCoupons();
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
		 return adminService.getCouponById(couponId);
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
	 
	 @PutMapping("/updateCoupon/{couponId}")
	 public Coupon updateCoupon(@RequestBody Coupon coupon,@PathVariable long couponId)
	 {
		 return adminService.updateCoupon(coupon, couponId);
	 }
	 
	 @DeleteMapping("/deleteCoupon/{couponId}")
	 public String deleteCoupon(@PathVariable long couponId)
	 {
		 return adminService.deleteCoupon(couponId);
	 }
	 
	 
	 @GetMapping("/getByName/{provider}")
	 @CircuitBreaker(name = "couponService", fallbackMethod = "viewByProviderFallback")
	 public Coupon viewByProvider(@PathVariable String provider)
	 {
		 return adminService.viewByCategory(provider);
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
		 return adminService.viewByCategory(category);
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
	 
	 
	
	 
	 //user Methods
	 
	   @PostMapping("/addUser")
	   @CircuitBreaker(name="userService" , fallbackMethod="addUserFallback")
		public User addUser(@Valid @RequestBody User user)
		{
			return adminService.addUser(user);
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
			return adminService.getAllUser();
			
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
			return adminService.deleteUser(userId);
		}
		
		@PutMapping("/updateUser/{userId}")
		public User updateUser(@RequestBody User user,@PathVariable long userId)
		{
			return adminService.updateUser(user, userId);
		}
}
