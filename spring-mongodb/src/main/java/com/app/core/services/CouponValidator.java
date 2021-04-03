package com.app.core.services;

import java.util.Date;
import java.util.List;

import com.app.core.models.Coupon;
import com.app.core.models.Customer;


public class CouponValidator {

	private Customer customer;
	
	public CouponValidator(Customer customer) {
		this.customer = customer;
	}
	
	/**
	 * @param couponId
	 * @return true if customer already purchased this coupon
	 */
	public boolean isCouponAlredyPurchased(String couponId) {
		List<String> ids = customer.getCouponsId();
		if (ids == null || ids.isEmpty()) {
			return false;
		}
		for (String id : ids) {
			if (couponId.equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param coupon
	 * @return true if coupon amount > 0
	 */
	public boolean isCouponAvailable(Coupon coupon) {
		if(coupon.getAmount() > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param coupon
	 * @return true if coupon not expired
	 */
	public boolean isCouponExpiered(Coupon coupon) {
		if (coupon.getEndDate().compareTo(new Date()) > 0) {
			return false;
		}
		return true;
	}
}
