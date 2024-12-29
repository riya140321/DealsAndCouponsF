package com.coupon.couponservice.CouponRepository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coupon.couponservice.Entity.Coupon;

@Repository
public interface CouponRepository  extends JpaRepository<Coupon, Long>{

	
	//custom query
	Coupon findByProvider(String provider);

	Coupon findByCategoryIgnoreCaseContaining(String category);

	

}
