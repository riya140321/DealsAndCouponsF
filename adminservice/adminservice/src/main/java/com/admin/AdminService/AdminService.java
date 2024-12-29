package com.admin.AdminService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.admin.Model.Admin;
import com.admin.Model.Coupon;
import com.admin.Model.User;



public interface AdminService {

	
	//user methods -->
	
	public User addUser( User user);
	public List<User> getAllUser();
	public String deleteUser( long userId);
	public User updateUser( User user, long userId);
	
	
	
	
	//coupons methods -->
	
	
	
	
	
	 public String addCoupon( Coupon coupon);
	 public List<Coupon> getAllCoupons();
	 public Coupon getCouponById( long couponId);
	 public Coupon updateCoupon( Coupon coupon, long couponId);
	 public String deleteCoupon( long couponId);
	 public Coupon viewByProvider( String provider);
	 public Coupon viewByCategory( String category);
	
	 
	 
	 
	 
	 //admin methods-->
	 
	 
	 
	 
	
		public Admin addAdmin( Admin admin);
		public List<Admin> getAll();
		public String deleteAdmin( long adminId);
		public Admin updateAdmin( Admin admin, long adminId);

}
