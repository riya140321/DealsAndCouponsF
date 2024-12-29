package com.coupon.couponservice.Exception;

public class CouponNotFoundException extends RuntimeException{
	
	public CouponNotFoundException(String message)
	{
		super(message);
	}

}
