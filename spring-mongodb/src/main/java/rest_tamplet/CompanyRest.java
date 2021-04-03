package rest_tamplet;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.app.core.models.Company;
import com.app.core.models.Coupon;

public class CompanyRest {

	private RestTemplate restTemplate = new RestTemplate();
	private HttpHeaders headers = new HttpHeaders();
	
	public CompanyRest(String token) {
		super();
		headers.set("token", token);
	}
	
	public HttpHeaders getHeaders() {
		return headers;
	}
	
	public Coupon addCoupon(Coupon coupon) {
		System.out.println("addCoupon");
		try {
			String url = "http://localhost:8080/api/add-coupon";
			HttpEntity<Coupon> companyHttpEntity = new HttpEntity<Coupon>(coupon, headers);
			ResponseEntity<Coupon> response = restTemplate.postForEntity(url, companyHttpEntity, Coupon.class);
			System.out.println("coupon added: " + response.getBody());
			return response.getBody();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Coupon updateCoupon(Coupon coupon) {
		System.out.println("updateCoupon");
		try {
			String url = "http://localhost:8080/api/update-coupon";
			HttpEntity<Coupon> httpEntity = new HttpEntity<Coupon>(coupon, headers);
			ResponseEntity<Coupon> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Coupon.class);
			System.out.println("coupon updated: " + response.getBody());
			return response.getBody();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Coupon deleteCoupon(Long id) {
		System.out.println("deleteCoupon");
		try {
			String url = "http://localhost:8080/api/delete-coupon/" + id;
			HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
			ResponseEntity<Coupon> response = restTemplate.exchange(url,  HttpMethod.DELETE, httpEntity, Coupon.class);
			System.out.println("coupon deleted: " + response.getBody());
			return response.getBody();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Coupon> getCouponsPriceLessThen(double maxPrice) {
		System.out.println("getCouponsPriceLessThen");
		try {
			String url = "http://localhost:8080/api/company/coupons/" + maxPrice;
			HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
			ResponseEntity<Coupon[]> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Coupon[].class);
			List<Coupon> coupons = Arrays.asList(response.getBody());
			System.out.println("companies: " + coupons);
			return coupons;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Coupon> getAllCoupons() {
		System.out.println("getAllCoupons");
		try {
			String url = "http://localhost:8080/api/company/coupons";
			HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
			ResponseEntity<Coupon[]> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Coupon[].class);
			List<Coupon> coupons = Arrays.asList(response.getBody());
			System.out.println("coupons: " + coupons);
			return coupons;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Company getCompanyDetails() {
		System.out.println("getCompanyDetails");
		try {
			String url = "http://localhost:8080/api/company";
			HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
			ResponseEntity<Company> response = restTemplate.exchange(url,  HttpMethod.GET, httpEntity, Company.class);
			System.out.println("company: " + response.getBody());
			return response.getBody();
		} catch (Exception e) {
			throw e;
		}
	}
}
