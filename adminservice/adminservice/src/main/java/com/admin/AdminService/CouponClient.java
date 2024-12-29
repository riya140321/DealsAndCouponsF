package com.admin.AdminService;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.admin.Model.Coupon;

import jakarta.validation.Valid;

@FeignClient(name="COUPON-SERVICE")
public interface CouponClient {
	
	
	
	@PostMapping("/coupon/add")
	 public String addCoupon(@Valid @RequestBody Coupon coupon);
	
	 
	 @GetMapping("/coupon/getAllCoupons")
	 public List<Coupon> getAllCoupons();
	 
	 
	 @GetMapping("/coupon/getById/{couponId}")
	 public Coupon getCouponById(@PathVariable long couponId);
	
	 
	 @PutMapping("/coupon/updateCoupon/{couponId}")
	 public Coupon updateCoupon(@RequestBody Coupon coupon,@PathVariable long couponId);
	 
	 @DeleteMapping("/coupon/coupdeleteCoupon/{couponId}")
	 public String deleteCoupon(@PathVariable long couponId);
	 
	 
	 @GetMapping("/coupon/getByName/{provider}")
	 public Coupon viewByProvider(@PathVariable String provider);
	 
	 
	 @GetMapping("/coupon/getByCategory/{category}")
	 public Coupon viewByCategory(@PathVariable String category);
	 
	 
	 
	 
	
	

}
