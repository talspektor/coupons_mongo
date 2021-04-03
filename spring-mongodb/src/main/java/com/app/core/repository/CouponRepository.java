package com.app.core.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.app.core.models.Category;
import com.app.core.models.Coupon;


public interface CouponRepository extends MongoRepository<Coupon, String> {
	
	List<Coupon> findAllByOrderByCategory();
	
	Optional<Coupon> findByTitle(String title);
	
//	List<Coupon> findAllByCompanyIdAndPriceLessThen(String id, double price);
	
	List<Coupon> findAllByCompanyId(String id);
	
	List<Coupon> findAllByIdAndCategory(List<String> ids, Category category);
		
	boolean existsByTitleAndCompanyId(String title, String companyId);

	List<Coupon> findAllByCategory(Category category);
	@Transactional
	void removeByEndDateLessThan(Date endDate);
}
