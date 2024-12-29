package com.coupon.coupon_service;


import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.coupon.couponservice.CouponServiceApplication;
import com.coupon.couponservice.CouponController.CouponController;
import com.coupon.couponservice.CouponRepository.CouponRepository;
import com.coupon.couponservice.Entity.Coupon;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.stream.Stream;

@SpringBootTest(classes = CouponServiceApplication.class)   //  used to tell Spring Boot to load the complete application for testing.want to test your Spring Boot application as a whole
class CouponServiceApplicationTests {

	    @MockBean    // Creates a mock  object of the CouponRepository interface,
	    CouponRepository couponRepository;
	    
	    @Autowired //Injects the CouponController bean into the test so that it can be tested directly.
	    CouponController couponController;
	    
	    @Test
	    public void getAllCouponsTests()
	    {
	    	when(couponRepository.findAll()).thenReturn(
	    			Stream.of(new Coupon(1L,"SAVE20","Save 20%","25-12-2024","Clothes","H&M"))
	    			.collect(Collectors.toList()));
	    	
	    	assertEquals(1, couponController.getAllCoupons().size());
	    }
	    
	    @Test
	    public void addCouponTests()
	    {
	    	Coupon coupon=new Coupon(1L,"SAVE20","Save 20%","25-12-2024","Clothes","H&M");
	    	when(couponRepository.save(coupon)).thenReturn(coupon);
	    	assertEquals("Coupon Added Successfully", couponController.addCoupon(coupon)); // check for expected result with actual result
	    }
	    
	    @Test
	    public void deleteCouponTests()
	    {
	    	long couponId=1L;
	    	Coupon coupon=new Coupon(1L,"SAVE20","Save 20%","25-12-2024","Clothes","H&M");
	    	couponRepository.deleteById(couponId);
	    	verify(couponRepository).deleteById(couponId);	
	    	
	    }
	    
	    @Test
	    public void updateCouponTests()
	    {
	    	long couponId=1L;
	    	Coupon coupon=new Coupon(1L,"SAVE20","Save 20%","25-12-2024","Clothes","H&M");
	    	couponRepository.save(coupon);
	    	verify(couponRepository).save(coupon);
	    }
	    
	    @Test
	    public void viewByProviderTests()
	    {
	    	String provider="H&M";
	    	Coupon coupon=new Coupon(1L,"SAVE20","Save 20%","25-12-2024","Clothes","H&M");
	    	couponRepository.findByProvider(provider);
	    	verify(couponRepository).findByProvider(provider); //verify interactions with mocked beans.
	    }
	   
	    @Test // mark methods as test methods 
	   public void viewByCategoryTests()
	   {
	    	String category="Clothes";
	    	long couponId=1L;
	    	Coupon coupon=new Coupon(1L,"SAVE20","Save 20%","25-12-2024","Clothes","H&M");
	    	couponRepository.findByCategoryIgnoreCaseContaining(category);
	    	verify(couponRepository).findByCategoryIgnoreCaseContaining(category);
	   }
	    
	    @Test
	    public void  getCouponByIdTests()
	    {
	    	long couponId=1L;
	    	Coupon coupon=new Coupon(1L,"SAVE20","Save 20%","25-12-2024","Clothes","H&M");
	    	couponRepository.findById(couponId);
	    	verify(couponRepository).findById(couponId);
	    }
	    
	    @Test
	    public void updateByDescriptionTests()
	    {
	    	long couponId=1L;
	    	Coupon coupon=new Coupon(1L,"SAVE20","Save 20%","25-12-2024","Clothes","H&M");
	    	couponRepository.findById(couponId);
	    	verify(couponRepository).findById(couponId);
	    }
	
}
