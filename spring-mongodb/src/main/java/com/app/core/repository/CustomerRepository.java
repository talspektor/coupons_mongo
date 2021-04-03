package com.app.core.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.core.models.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
		
	Optional<Customer> findByEmailAndPassword(String email, String password);
	
	boolean existsByEmail(String email);
	
	boolean existsByPassword(String password);
	
	Optional<Customer> findByEmail(String email);
}
