package rest_tamplet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Login {

	private RestTemplate restTemplate = new RestTemplate();

	public String adminLogin() throws Exception {
		try {
			String url = "http://localhost:8080/login/ADMIN/com.admin@admin/admin";
			String login = "";
			ResponseEntity<String> response = restTemplate.postForEntity(url, login, String.class);
			System.out.println("Admin Token: " + response.getBody());
			return response.getBody();
		} catch (Exception e) {
			throw e;
		}
	}

	public String companyLogin(String email, String password) throws Exception {
		try {
			String url = "http://localhost:8080/login/COMPANY/" + email + "/" + password;
			String login = "";
			ResponseEntity<String> response = restTemplate.postForEntity(url, login, String.class);
			System.out.println("Company Token: " + response.getBody());
			return response.getBody();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public String customerLogin(String email, String password) throws Exception {
		try {
			String url = "http://localhost:8080/login/CUSTOMER/" + email + "/" + password;
			String login = "";
			ResponseEntity<String> response = restTemplate.postForEntity(url, login, String.class);
			System.out.println("Customer Token: " + response.getBody());
			return response.getBody();
		} catch (Exception e) {
			throw e;
		}
	}
}
