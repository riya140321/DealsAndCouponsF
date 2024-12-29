package com.coupon.couponservice.CouponController;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.coupon.couponservice.CouponRepository.CouponRepository;
import com.coupon.couponservice.Entity.Coupon;
import com.coupon.couponservice.Services.CouponServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/coupon")
public class CouponController {

	 @Autowired
	 private  CouponServiceImpl couponServiceImpl;
	 
	 
	 @PostMapping("/add")
	 public String addCoupon(@Valid @RequestBody Coupon coupon) 
	 {
	 	return couponServiceImpl.addCoupon(coupon);
	 }
	 
	 
	 
	 
	 @GetMapping("/getAllCoupons")
	 public List<Coupon> getAllCoupons()
	 {
		 return couponServiceImpl.getAllCoupons();
	 }
	 
	 @GetMapping("/getById/{couponId}")
	 public Coupon getCouponById(@PathVariable long couponId)
	 {
		 return couponServiceImpl.getCouponById(couponId);
	 }
	 
	 @PutMapping("/updateCoupon/{couponId}")
	 public Coupon updateCoupon(@RequestBody Coupon coupon,@PathVariable long couponId)
	 {
		
		 return couponServiceImpl.updateCoupon(coupon, couponId);
		 
	 }
	 @DeleteMapping("/deleteCoupon/{couponId}")
	 public String deleteCoupon(@PathVariable long couponId)
	 {
		 
	    return couponServiceImpl.deleteCoupon(couponId);
	 }
	 
	 @GetMapping("/getByName/{provider}")
	 public Coupon viewByProvider(@PathVariable String provider)
	 {
		 return couponServiceImpl.viewByProvider(provider);
	
	 }
	 
	 @GetMapping("/getByCategory/{category}")
	 public Coupon viewByCategory(@PathVariable String category)
	 {
		 
		 return couponServiceImpl.viewByCategory(category); 
	 }
	 
	
}
