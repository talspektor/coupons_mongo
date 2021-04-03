package com.app.core.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "company")
public class Company {

	@Id
	private String id;
	private String name;
	private String email;
	private String password;
	@JsonIgnore
	private List<String> couponsId = new ArrayList<String>();
	
	public Company() {
		super();
	}
	public Company(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	public List<String> getCoupons() {
		return couponsId;
	}
	
	public String getCoupon(String id) {
		for (String couponId : couponsId) {
			if (couponId.equals(id)) {
				return couponId;
			}
		}
		return null;
	}
	
	public void addCouponId(String couponId) {
		this.couponsId.add(couponId);
	}
	
	public String deleteCouponId(String id) {
		if(id == null) { return null; }
		for (String couponId : couponsId) {
			if (couponId.equals(id)) {
				couponsId.remove(couponId);
				return couponId;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "CompanyEntitiy [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
	
}
