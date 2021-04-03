package com.app.core.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.app.core.filters.LoginFilter;
import com.app.core.models.Company;
import com.app.core.repository.CompanyRepository;
import com.app.core.session.SessionContext;

//@EnableMongoRepositories(basePackageClasses = BookRepository.class)
@Configuration
public class MongoDBConfig {
	
	@Autowired
	CompanyRepository repositiry;

//	@Bean
//	CommandLineRunner commandLineRunner(BookRepository repository) {
//		return new CommandLineRunner() {
//			
//			@Override
//			public void run(String... args) throws Exception {
//				Optional<Company> company = repositiry.findById("6062072833356b5629bfe556");
//				System.out.println(company.get());
//				Book b1 = new Book("b1", "tal");
//				Book b2 = new Book("b2", "ethan");
//				Book b3 = new Book("b3", "avri");
//				
//				repository.save(b1);
//				repository.save(b2);
//				repository.save(b3);
//				
//				System.out.println("************");
//				
//				List<Book> books = repository.findAll();
//				for (Book book : books) {
//					System.out.println(book);
//				}
//			}
//		};
//	}
	
	@Bean
	public FilterRegistrationBean<LoginFilter> filterRegisrtation(SessionContext sessionContext) {
		FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<LoginFilter>();
		LoginFilter loginFilter = new LoginFilter(sessionContext);
		filterRegistrationBean.setFilter(loginFilter);
		filterRegistrationBean.addUrlPatterns("/api/*");
		return filterRegistrationBean;
	}
}
