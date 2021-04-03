package com.app.core.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.core.exceptions.CouponSystemException;
import com.app.core.models.Company;
import com.app.core.models.Customer;
import com.app.core.repository.CompanyRepository;
import com.app.core.repository.CustomerRepository;

@Transactional
@Service
@Scope(value = "prototype")
public class AdminService implements ClientService {
	
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	public AdminService() {
		super();
	}

	/**
	 * @param email
	 * @param password
	 * @return true if email and password are correct
	 */
	public boolean login(String email, String password) throws CouponSystemException {
		if (email == null || password == null) {
			throw new CouponSystemException(HttpStatus.NOT_ACCEPTABLE, "login fail :( email or password are null");
		}
		if (email.equals("com.admin@admin") && password.equals("admin")) {
			return true;
		}
		return false;
	}
	
	// *********** Company Methods ************* //
	
	/**
	 * @param company
	 * @throws CouponSystemException
	 * add company to database email and name must by unique
	 */
	public Company addCompany(Company company) throws CouponSystemException {
		if(company == null) {
			throw new CouponSystemException(HttpStatus.NOT_ACCEPTABLE, "company is null...");
		}
		try {
			if(isCompanyNameExists(company.getName()) || isCompanyEmailExists(company.getEmail())) {
				throw new CouponSystemException(HttpStatus.BAD_REQUEST, "can't add company name and email must by unique");
			}
			companyRepository.save(company);
			Optional<Company> optCompany = companyRepository.findByName(company.getName());
			if(optCompany.isPresent()) {
				return optCompany.get();
			}
			throw new CouponSystemException(HttpStatus.CREATED, "server fail to get company from database");
		} catch (CouponSystemException e) {
			throw e;
		} catch (DataAccessException e) {
			throw new CouponSystemException(HttpStatus.SERVICE_UNAVAILABLE, "addCompany fail " + e.getMessage(), e);
		} catch (Exception e) {
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, "addCompany fail " + e.getMessage(), e);
		}
	}
	
	/**
	 * @param company
	 * @throws CouponSystemException
	 * update company in database (don't change company name) 
	 */
	public Company updateCompany(Company company) throws CouponSystemException {
		if (company == null) {
			throw new CouponSystemException(HttpStatus.NOT_ACCEPTABLE, "can't update company - company is null");
		}
		try {
			Optional<Company> companyToUpdate = companyRepository.findById(company.getId());
			if (companyToUpdate.isPresent()) {
				if (!company.getName().equals(companyToUpdate.get().getName())) {
					throw new CouponSystemException(HttpStatus.BAD_REQUEST, "You can't change company name");
				}
				companyToUpdate.get().setName(company.getName());
				companyToUpdate.get().setEmail(company.getEmail());
				companyToUpdate.get().setPassword(company.getPassword());
				companyRepository.save(companyToUpdate.get());
				return companyToUpdate.get();
			}
			throw new CouponSystemException(HttpStatus.BAD_REQUEST, "company: " + company + " is not found is database");
		} catch (CouponSystemException e) {
			throw e;
		} catch (DataAccessException e) {
			throw new CouponSystemException(HttpStatus.SERVICE_UNAVAILABLE, "updateCompany fail " + e.getMessage(), e);
		} catch (Exception e) {
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, "updateCompany fail " + e.getMessage(), e);
		}
	}
	
	/**
	 * @param companyId
	 * @throws CouponSystemException
	 * delete all company coupon and then delete the company
	 */ 
	public Company deleteCoumpany(String companyId) throws CouponSystemException {
		if (companyId == null) {
			throw new CouponSystemException(HttpStatus.NOT_ACCEPTABLE, "Id is null");
		}
		try {
			Optional<Company> optCompany = companyRepository.findById(companyId);
			if(optCompany.isPresent()) {
				companyRepository.deleteById(companyId);
				return optCompany.get();
			}
			throw new CouponSystemException(HttpStatus.BAD_REQUEST, "deleteCoumpany fail company is not in database");
		} catch (CouponSystemException e) {
			throw e;
		} catch (IllegalArgumentException e) {
			throw new CouponSystemException(HttpStatus.NOT_FOUND, "deleteCoumpany fail company is not in database", e);
		} catch (DataAccessException e) {
			throw new CouponSystemException(HttpStatus.SERVICE_UNAVAILABLE, "deleteCoumpany fail " + e.getMessage(), e);
		} catch (Exception e) {
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, "deleteCoumpany fail " + e.getMessage(), e);
		}
	}
	
	/**
	 * @return all companies from database
	 * @throws CouponSystemException
	 */
	public List<Company> getAllCompanies() throws CouponSystemException {
		try {
			return (List<Company>) companyRepository.findAll();
		} catch (CouponSystemException e) {
			throw e;
		} catch (DataAccessException e) {
			throw new CouponSystemException(HttpStatus.SERVICE_UNAVAILABLE, "getAllCompanies fail " + e.getMessage(), e);
		} catch (Exception e) {
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR,"getAllCompanies fail :(" + e.getMessage(), e);
		}
	}
	
	/**
	 * @param companyId
	 * @return the company with that id
	 * @throws CouponSystemException
	 */
	public Company getCompany(String companyId) throws CouponSystemException {
		if (companyId == null) {
			throw new CouponSystemException(HttpStatus.NOT_ACCEPTABLE, "Id is null");
		}
		try {
			Optional<Company> optCompany = companyRepository.findById(companyId);
			if (optCompany.isPresent()) {
				return optCompany.get();
			}
			throw new CouponSystemException(HttpStatus.BAD_REQUEST, "getCompany fail: company not found in database");
		} catch (CouponSystemException e) {
			throw e;
		} catch (DataAccessException e) {
			throw new CouponSystemException(HttpStatus.SERVICE_UNAVAILABLE, "getCompany fail " + e.getMessage(), e);
		} catch (Exception e) {
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, "getCompany fail :(" + e.getMessage(), e);
		}
	}
	
	public Company getCompanyByName(String name) throws CouponSystemException {
		if (name == null) {
			throw new CouponSystemException(HttpStatus.NOT_ACCEPTABLE, "name is null");
		}
		try {
			Optional<Company> optCompany = companyRepository.findByName(name);
			if (optCompany.isPresent()) {
				return optCompany.get();
			}
			throw new CouponSystemException(HttpStatus.BAD_REQUEST, "getCompanyByName fail: company not found in database");
		} catch (CouponSystemException e) {
			throw e;
		} catch (DataAccessException e) {
			throw new CouponSystemException(HttpStatus.SERVICE_UNAVAILABLE, "getCompanyByName fail " + e.getMessage(), e);
		} catch (Exception e) {
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, "getCompanyByName fail " + e.getMessage(), e);
		}
	}
	
	// ********** Customer Methods ***************** //
	
	/**
	 * @param customer
	 * @throws CouponSystemException
	 * add customer to database if email and password are unique
	 */
	public Customer addCustomer(Customer customer) throws CouponSystemException {
		if (customer == null) {
			throw new CouponSystemException(HttpStatus.NOT_ACCEPTABLE, "customer is null");
		}
		try {
			if(isCustomerEmailExists(customer.getEmail())) {
				System.out.println("You can't add customer email and password most be unique");
				throw new CouponSystemException(HttpStatus.BAD_REQUEST, "You can't add customer email and password most be unique");
			}
			customerRepository.save(customer);
			Optional<Customer> optCustoemr = customerRepository.findByEmail(customer.getEmail());
			if(optCustoemr.isPresent()) {
				return optCustoemr.get();
			}
			throw new CouponSystemException(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "addCustomer fail ");
		} catch (CouponSystemException e) {
			throw e;
		} catch (DataAccessException e) {
			throw new CouponSystemException(HttpStatus.SERVICE_UNAVAILABLE, "addCustomer fail " + e.getMessage(), e);
		} catch (Exception e) {
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, "addCustomer fail " + e.getMessage(), e);
		}
	}
	
	/**
	 * @param customer
	 * @throws CouponSystemException
	 * update customer in database 
	 */
	public Customer updateCustomer(Customer customer) throws CouponSystemException {
		if (customer == null) {
			throw new CouponSystemException(HttpStatus.NOT_ACCEPTABLE, "customer is null");
		}
		try {
			Optional<Customer> optCustomer = customerRepository.findById(customer.getId());
			if (!optCustomer.isPresent()) {
				throw new CouponSystemException(HttpStatus.BAD_REQUEST, "Costomer not found in database");
			}
			optCustomer.get().setFirstName(customer.getFirstName());
			optCustomer.get().setLastName(customer.getLastName());
			optCustomer.get().setPassword(customer.getPassword());
			optCustomer.get().setEmail(customer.getEmail());
			customerRepository.save(optCustomer.get());
			return customer;
		} catch (CouponSystemException e) {
			throw e;
		} catch (IllegalArgumentException e) {
			throw new CouponSystemException(HttpStatus.BAD_REQUEST, "updateCustomer fail eimal moat be uniqua", e);
		} catch (DataAccessException e) {
			throw new CouponSystemException(HttpStatus.SERVICE_UNAVAILABLE, "updateCustomer fail " + e.getMessage(), e);
		} catch (Exception e) {
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, "updateCustomer fail " + e.getMessage(), e);
		}
	}
	
	/**
	 * @param customerId
	 * @throws CouponSystemException
	 * delete customer coupon purchases and delete customer from database
	 */
	public Customer deleteCustomer(String customerId) throws CouponSystemException {
		if (customerId == null) {
			throw new CouponSystemException(HttpStatus.NOT_ACCEPTABLE, "customerId is null");
		}
		try {
			Optional<Customer> optCustomer = customerRepository.findById(customerId);
			if(optCustomer.isPresent()) {
				customerRepository.deleteById(customerId);
				return optCustomer.get();
			}
			throw new CouponSystemException(HttpStatus.BAD_REQUEST, "deleteCustomer fail: customer with id: " + customerId + " not found in database");
		} catch (CouponSystemException e) {
			throw e;
		} catch (DataAccessException e) {
			throw new CouponSystemException(HttpStatus.SERVICE_UNAVAILABLE, "deleteCustomer fail " + e.getMessage(), e);
		} catch (Exception e) {
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, "deleteCustomer fail " + e.getMessage(), e);
		}
	}
	
	/**
	 * @return list of all the customers from database
	 * @throws CouponSystemException
	 */
	public List<Customer> getAllCustomer() throws CouponSystemException {
		try {
			return (List<Customer>) customerRepository.findAll();
		} catch (CouponSystemException e) {
			throw e;
		} catch (DataAccessException e) {
			throw new CouponSystemException(HttpStatus.SERVICE_UNAVAILABLE, "getAllCustomer fail " + e.getMessage(), e);
		} catch (Exception e) {
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, "getAllCustomer fail " + e.getMessage(), e);
		}
	}
	
	/**
	 * @param customerId
	 * @return the customer with the customer id from database
	 * @throws CouponSystemException
	 */
	public Customer getCustomer(String customerId) throws CouponSystemException {
		if (customerId == null) {
			throw new CouponSystemException(HttpStatus.NOT_ACCEPTABLE, "customerId is null");
		}
		try {
			Optional<Customer> optCustomer = customerRepository.findById(customerId);
			if(optCustomer.isPresent()) {
				return optCustomer.get();
			}
			throw new CouponSystemException(HttpStatus.BAD_REQUEST, "Costomer with id=" + customerId + " not found in database");
		} catch (CouponSystemException e) {
			throw e;
		} catch (DataAccessException e) {
			throw new CouponSystemException(HttpStatus.SERVICE_UNAVAILABLE, "getCustomer fail " + e.getMessage(), e);
		} catch (Exception e) {
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, "getCustomer fail " + e.getMessage(), e);
		}
	}
	
	//************** Company Unique check  Methods ********************* //
	
	/**
	 * @param name
	 * @return true if company with given name is in database
	 * @throws CouponSystemException
	 */
	private boolean isCompanyNameExists(String name) throws CouponSystemException {
		try {
			return companyRepository.existsByName(name);
		} catch (CouponSystemException e) {
			throw e;
		} catch (DataAccessException e) {
			throw new CouponSystemException(HttpStatus.SERVICE_UNAVAILABLE, "isCompanyNameUnique fail " + e.getMessage(), e);
		} catch (Exception e) {
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, "isCompanyNameUnique fail " + e.getMessage(), e);
		}
	}
	
	/**
	 * @param email
	 * @return true if company with given email is in database
	 * @throws CouponSystemException
	 */
	private boolean isCompanyEmailExists(String email) throws CouponSystemException {
		try {
			return companyRepository.existsByEmail(email);
		} catch (CouponSystemException e) {
			throw e;
		} catch (DataAccessException e) {
			throw new CouponSystemException(HttpStatus.SERVICE_UNAVAILABLE, "isCompanyEmailExists fail " + e.getMessage(), e);
		} catch (Exception e) {
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, "isCompanyEmailUnique fail " + e.getMessage(), e);
		}
	}
	
	//************** Customer Unique check  Methods ********************* //
	
	/**
	 * @param email
	 * @return true if customer with email is exist is database
	 * @throws CouponSystemException
	 */
	private boolean isCustomerEmailExists(String email) throws CouponSystemException {
		try {
			return customerRepository.existsByEmail(email);
		} catch (CouponSystemException e) {
			throw e;
		} catch (DataAccessException e) {
			throw new CouponSystemException(HttpStatus.SERVICE_UNAVAILABLE, "isCustomerEmailExists fail " + e.getMessage(), e);
		} catch (Exception e) {
			throw new CouponSystemException(HttpStatus.INTERNAL_SERVER_ERROR, "isCustomerEmailExists fail " + e.getMessage(), e);
		}
	}
}
