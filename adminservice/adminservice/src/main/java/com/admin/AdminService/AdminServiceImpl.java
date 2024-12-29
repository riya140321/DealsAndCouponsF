package com.admin.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.admin.Model.Admin;
import com.admin.Model.Coupon;
import com.admin.Model.User;
import com.admin.Repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private CouponClient   couponClient;
	
	
	@Autowired
	private UserClient userClient;
	

	public Admin addAdmin( Admin admin)
	{
		return adminRepository.save(admin);
	}
	
	public List<Admin> getAll()
	{
		return adminRepository.findAll();
	}
	
	public String deleteAdmin( long adminId)
	{
	  adminRepository.deleteById(adminId);
	  return "Admin Deleted Successfully";
	}
	
	public Admin updateAdmin( Admin admin, long adminId)
	{
		Admin value= adminRepository.findById(adminId).orElse(null);
		
			value.setAdminName(admin.getAdminName());
			value.setEmail(admin.getEmail());
			value.setPassword(admin.getPassword());
			value.setUsername(admin.getUsername());
			return adminRepository.save(value);
		
	     
	}
	
	//coupon methods
	
	public String addCoupon( Coupon coupon)
	{
		return couponClient.addCoupon(coupon);
	}
	
	
	 public List<Coupon> getAllCoupons()
	 {
		 return couponClient.getAllCoupons();
	 }
	 
	 public Coupon getCouponById( long couponId)
	 {
		 return couponClient.getCouponById(couponId);
	 }
	 
	 public Coupon updateCoupon( Coupon coupon, long couponId)
	 {
		 return couponClient.updateCoupon(coupon, couponId);
	 }
	 
	 public String deleteCoupon( long couponId)
	 {
		 return couponClient.deleteCoupon(couponId);
	 }
	 
	 
	 public Coupon viewByProvider( String provider)
	 {
		 return couponClient.viewByProvider(provider);
	 }
	 
	 public Coupon viewByCategory( String category)
	 {
		 return couponClient.viewByCategory(category);
	 }
	 
	 
	
	//user method
	 

		public User addUser( User user)
		{
			return  userClient.addUser(user);
		}
		public List<User> getAllUser()
		{
			return userClient.getAllUser();
		}
		
		public String deleteUser( long userId)
		{
			return userClient.deleteUser(userId);
		}
		public User updateUser( User user, long userId)
		{
			return userClient.updateUser(user, userId);
		}

}
