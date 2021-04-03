package rest_tamplet;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.app.core.models.Category;
import com.app.core.models.Coupon;
import com.app.core.models.Customer;

public class CustomerRest {

	private RestTemplate restTemplate = new RestTemplate();
	private HttpHeaders headers = new HttpHeaders();
	
	public CustomerRest(String token) {
		super();
		headers.set("token", token);
	}
	
	
	public HttpHeaders getHeaders() {
		return headers;
	}
	
	public Coupon purchaseCoupon(String id) {
		System.out.println("purchaseCoupon");
		try {
			String url = "http://localhost:8080/api/purchase-coupon/" + id;
			HttpEntity<Object> companyHttpEntity = new HttpEntity<Object>(headers);
			ResponseEntity<Coupon> response = restTemplate.exchange(url, HttpMethod.PUT, companyHttpEntity, Coupon.class);
			System.out.println("coupon purchase: " + response.getBody());
			return response.getBody();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Coupon> getCustomerCoupons() {
		System.out.println("getCustomerCoupons");
		try {
			String url = "http://localhost:8080/api/customer/coupons";
			HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
			ResponseEntity<Coupon[]> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Coupon[].class);
			List<Coupon> coupons = Arrays.asList(response.getBody());
			System.out.println("coupons: " + coupons);
			return coupons;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Coupon> getAllCoupons() {
		System.out.println("getAllCoupons");
		try {
			String url = "http://localhost:8080/api/coupons";
			HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
			ResponseEntity<Coupon[]> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Coupon[].class);
			List<Coupon> coupons = Arrays.asList(response.getBody());
			System.out.println("coupons: " + coupons);
			return coupons;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Coupon>getCouponsByCategory(Category category) {
		System.out.println("getCouponsByCategory");
		try {
			String url = "http://localhost:8080/api/customer/coupons/category/" + category;
			HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
			ResponseEntity<Coupon[]> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Coupon[].class);
			List<Coupon> coupons = Arrays.asList(response.getBody());
			System.out.println("coupons: " + coupons);
			return coupons;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Coupon>getCouponsPriceLessThen(double maxPrice) {
		System.out.println("getCouponsPriceLessThen");
		try {
			String url = "http://localhost:8080/api/customer/coupons/maxPrice/" + maxPrice;
			HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
			ResponseEntity<Coupon[]> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Coupon[].class);
			List<Coupon> coupons = Arrays.asList(response.getBody());
			System.out.println("coupons: " + coupons);
			return coupons;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Customer getCustomerDetails() {
		System.out.println("getCustomerDetails");
		try {
			String url = "http://localhost:8080/api/customer";
			HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
			ResponseEntity<Customer> response = restTemplate.exchange(url,  HttpMethod.GET, httpEntity, Customer.class);
			System.out.println("company: " + response.getBody());
			return response.getBody();
		} catch (Exception e) {
			throw e;
		}
	}
}
