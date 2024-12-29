package com.coupon.couponservice.Services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coupon.couponservice.CouponRepository.CouponRepository;
import com.coupon.couponservice.Entity.Coupon;
import com.coupon.couponservice.Exception.CouponNotFoundException;

@Service
public class CouponServiceImpl  implements CouponService{
	
	@Autowired
	private CouponRepository couponRepository;
	
	
	public String addCoupon(Coupon coupon) 
	{
		couponRepository.save(coupon);
	 	return "Coupon Added Successfully";
	}
	
	public List<Coupon> getAllCoupons()
	{
		return couponRepository.findAll();
	}
	
	public Coupon getCouponById(long couponId)
	{
		return couponRepository.findById(couponId).orElseThrow(()-> new CouponNotFoundException("CouponId Not Found"));
	}
	
	public Coupon updateCoupon(Coupon coupon, long couponId)
	{
		Coupon value= getCouponById(couponId);
		value.setCategory(coupon.getCategory());
		value.setCouponCode(coupon.getCouponCode());
		value.setDescription(coupon.getDescription());
		value.setExpiryDate(coupon.getExpiryDate());
		value.setProvider(coupon.getProvider());
		return couponRepository.save(value);
	}
	
	
	public String deleteCoupon( long couponId)
	{
		couponRepository.deleteById(couponId);
		
		 return "Deleted Successfully";
	}
	
	public Coupon viewByProvider( String provider)
	{
		 return couponRepository.findByProvider(provider);
	}
	
	public Coupon viewByCategory(String category)
	{
		
		Coupon coupon  = couponRepository.findByCategoryIgnoreCaseContaining(category);
		 if(coupon==null)
		 {
			 throw new RuntimeException("Category Mismatched");
		 } 
		
		 return coupon;
	}
	
	

}
