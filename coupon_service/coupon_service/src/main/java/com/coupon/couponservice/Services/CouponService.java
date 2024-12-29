package com.coupon.couponservice.Services;


import java.util.List;

import com.coupon.couponservice.Entity.Coupon;

public interface CouponService {

	
	String addCoupon(Coupon coupon);
	List<Coupon> getAllCoupons();
    Coupon getCouponById(long couponId);
	Coupon updateCoupon(Coupon coupon, long couponId);
	String deleteCoupon( long couponId);
	Coupon viewByProvider( String provider);
	Coupon viewByCategory( String category);
    
    
}