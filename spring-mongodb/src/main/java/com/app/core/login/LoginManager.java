package com.app.core.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.app.core.exceptions.CouponSystemException;
import com.app.core.services.AdminService;
import com.app.core.services.ClientService;
import com.app.core.services.CompanyService;
import com.app.core.services.CustomerService;

@Component
public class LoginManager {
	@Autowired
	private ApplicationContext context;
	
	/**
	 * @param email
	 * @param password
	 * @param clientType
	 * @return the service
	 * @throws CouponSystemException
	 */
	public ClientService login(String email, String password, ClientType clientType) throws CouponSystemException {
		ClientService service = null;
		switch (clientType) {
		case ADMIN:
			AdminService adminService = context.getBean(AdminService.class);
			if (adminService.login(email, password)) {
				service = adminService;
			}
			break;
		case COMPANY:
			CompanyService companyService = context.getBean(CompanyService.class);
			if (companyService.login(email, password)) {
				service = companyService;
			}
			break;
		case CUSTOMER:
			CustomerService customerService = context.getBean(CustomerService.class);
			if (customerService.login(email, password)) {
				service = customerService;
			}
			break;
		}
		if (service == null) {
			throw new CouponSystemException(HttpStatus.NOT_FOUND, "Wrong credentials: " + clientType + " email: " + email + " password: " + password);
		}
		return service;
	}
}
