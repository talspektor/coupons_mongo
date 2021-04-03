package com.app.core.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "customer")
public class Customer {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	@JsonIgnore
	private List<String> couponsId;

	public Customer() {
		super();
	}

	public Customer(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getCouponsId() {
		return couponsId;
	}

	public void setCoupons(List<String> couponsId) {
		this.couponsId = couponsId;
	}

	public void addCoupnId(String id) {
		if (couponsId == null) {
			couponsId = new ArrayList<String>();
		}
		couponsId.add(id);
		System.out.println("addCoupon: coupon - " + id + " was added");
	}
	
	public void deleteCouponId(String couponId) {
		if (couponId == null) { return; }
		for (String id : couponsId) {
			if (id.equals(couponId)) {
				couponsId.remove(id);
				return;
			}
		}
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}

}
