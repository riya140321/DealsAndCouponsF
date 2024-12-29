package com.admin.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coupon {

	
	private long couponId;
	private String couponCode;
	private String description;
	private String expiryDate;
	private String category;
	private String provider;
}
