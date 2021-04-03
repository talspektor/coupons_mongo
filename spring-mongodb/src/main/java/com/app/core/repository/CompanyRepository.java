package com.app.core.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.core.models.Company;

public interface CompanyRepository extends MongoRepository<Company, String> {

	boolean existsByEmailAndPassword(String email, String password);
	
	boolean existsByName(String name);
	
	boolean existsByEmail(String email);
	
	Optional<Company> findByEmailAndPassword(String email, String password);
	
	Optional<Company> findByName(String name);
}