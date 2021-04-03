package com.app.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.core.exceptions.CouponSystemException;
import com.app.core.models.Company;
import com.app.core.models.Customer;
import com.app.core.services.AdminService;
import com.app.core.session.Session;
import com.app.core.session.SessionContext;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AdminController {
	
	@Autowired
	private SessionContext sessionContext;
	
	@PostMapping("/add-company")
	public Company addCoumpany(@RequestBody Company company, @RequestHeader String token) {
		try {
			Session session = sessionContext.getSession(token);
			AdminService service = (AdminService) session.getAttritutes("service");
			Company addedCompany = service.addCompany(company);
			return addedCompany;
		} catch (CouponSystemException e) {
			System.err.println(e.getReason());
			throw e;
		} catch (Exception e) {
			System.err.println(e.getCause());
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}
	
	@PutMapping("/update-company")
	public Company updateCompany(@RequestBody Company company, @RequestHeader String token) {
		try {
			Session session = sessionContext.getSession(token);
			AdminService service = (AdminService) session.getAttritutes("service");
			Company updatedCompany = service.updateCompany(company);
			return updatedCompany;
		} catch (CouponSystemException e) {
			System.err.println(e.getReason());
			throw e;
		} catch (Exception e) {
			System.err.println(e.getCause());
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
		
	}
	
	@DeleteMapping("/delete-company/{id}")
	public Company deleteCompany(@PathVariable String id, @RequestHeader String token) {
		try {
			Session session = sessionContext.getSession(token);
			AdminService service = (AdminService) session.getAttritutes("service");
			Company  deletedCompany = service.deleteCoumpany(id);
			return deletedCompany;
		} catch (CouponSystemException e) {
			System.err.println(e.getReason());
			throw e;
		} catch (Exception e) {
			System.err.println(e.getCause());
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}
	
	@GetMapping("/companies")
	public List<Company> getAllCompanies(@RequestHeader String token) {
		try {
			Session session = sessionContext.getSession(token);
			AdminService service = (AdminService) session.getAttritutes("service");
			List<Company> companies = service.getAllCompanies();
			return companies;
		} catch (CouponSystemException e) {
			System.err.println(e.getReason());
			throw e;
		} catch (Exception e) {
			System.err.println(e.getCause());
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
		
	}
	
	@GetMapping("/company/{id}")
	public Company getCompany(@PathVariable String id, @RequestHeader String token) {
		try {
			Session session = sessionContext.getSession(token);
			AdminService service = (AdminService) session.getAttritutes("service");
			Company compnay = service.getCompany(id);
			return compnay;
		} catch (CouponSystemException e) {
			System.err.println(e.getReason());
			throw e;
		} catch (Exception e) {
			System.err.println(e.getCause());
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}
	
	@GetMapping("/company/name/{name}")
	public Company getCompanyByName(@PathVariable String name, @RequestHeader String token) throws CouponSystemException {
		try {
			Session session = sessionContext.getSession(token);
			AdminService service = (AdminService) session.getAttritutes("service");
			Company company = service.getCompanyByName(name);
			return company;
		} catch (CouponSystemException e) {
			System.err.println(e.getReason());
			throw e;
		} catch (Exception e) {
			System.err.println(e.getCause());
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}
	
	// ********** Customer Methods ***************** //
	
	@PostMapping("/add-customer")
	public Customer addCustomer(@RequestBody Customer customer, @RequestHeader String token) {
		try {
			Session session = sessionContext.getSession(token);
			AdminService service = (AdminService) session.getAttritutes("service");
			Customer addedCustomer = service.addCustomer(customer);
			return addedCustomer;
		} catch (CouponSystemException e) {
			System.err.println(e.getReason());
			throw e;
		} catch (Exception e) {
			System.err.println(e.getCause());
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
		
	}
	
	@PutMapping("/update-customer")
	public Customer updateCustomer(@RequestBody Customer customer, @RequestHeader String token) {
		try {
			Session session = sessionContext.getSession(token);
			AdminService service = (AdminService) session.getAttritutes("service");
			Customer updatedCustomer = service.updateCustomer(customer);
			return updatedCustomer;
		} catch (CouponSystemException e) {
			System.err.println(e.getReason());
			throw e;
		} catch (Exception e) {
			System.err.println(e.getCause());
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
		
	}
	
	@DeleteMapping("/delete-customer/{id}")
	public Customer deleteCustomer(@PathVariable String id, @RequestHeader String token) {
		try {
			Session session = sessionContext.getSession(token);
			AdminService service = (AdminService) session.getAttritutes("service");
			Customer deletedCustomer = service.deleteCustomer(id);
			return deletedCustomer;
		} catch (CouponSystemException e) {
			System.err.println(e.getReason());
			throw e;
		} catch (Exception e) {
			System.err.println(e.getCause());
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
		
	}
	
	@GetMapping("/customers")
	public List<Customer>  getAllCustomers(@RequestHeader String token) {
		try {
			Session session = sessionContext.getSession(token);
			AdminService service = (AdminService) session.getAttritutes("service");
			List<Customer> customers = service.getAllCustomer();
			return customers;
		} catch (CouponSystemException e) {
			System.err.println(e.getReason());
			throw e;
		} catch (Exception e) {
			System.err.println(e.getCause());
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}
	
	@GetMapping("/customer/{id}")
	public Customer getCustomer(@PathVariable String id, @RequestHeader String token) {
		try {
			Session session = sessionContext.getSession(token);
			AdminService service = (AdminService) session.getAttritutes("service");
			Customer customer = service.getCustomer(id);
			return customer;
		} catch (CouponSystemException e) {
			System.err.println(e.getReason());
			throw e;
		} catch (Exception e) {
			System.err.println(e.getCause());
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}
}
