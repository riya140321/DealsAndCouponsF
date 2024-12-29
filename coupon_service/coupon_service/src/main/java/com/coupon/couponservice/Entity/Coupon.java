package com.coupon.couponservice.Entity;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long couponId;
	@NotEmpty(message="CouponCode should not be Empty")
	private String couponCode;
	@NotEmpty(message="Description should not be Empty")
	private String description;
	@NotEmpty(message="ExpiryDate should not be Empty")
	private String expiryDate;
	@NotEmpty(message="Category should not be Empty")
	private String category;
	@NotEmpty(message="Provider should not be Empty")
	private String provider;
}
