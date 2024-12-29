package com.user.Services;

import java.util.List;

import com.user.Entity.Coupon;
import com.user.Entity.User;

public interface UserService {
	
	

	//user methods 
	User addUser( User user);
	List<User> getAllUser();
	String deleteUser( long userId);
	User updateUser( User user, long userId);
	
	//coupon methods 
	
	
//	 String addCoupon( Coupon coupon);
	 List<Coupon> getAllCoupons();
	 Coupon getCouponById( long couponId);
//	 Coupon updateCoupon( Coupon coupon, long couponId);
//	 String deleteCoupon( long couponId);
	 Coupon viewByProvider( String provider);
	 Coupon viewByCategory( String category);
	 
}
