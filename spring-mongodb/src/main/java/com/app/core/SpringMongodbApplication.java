package com.app.core;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.app.core.filters.LoginFilter;
import com.app.core.session.SessionContext;

//@Configuration
@SpringBootApplication
public class SpringMongodbApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringMongodbApplication.class, args);
	}
	
	
	
//	@Bean
//	public FilterRegistrationBean<LoginFilter> filterRegisrtation(SessionContext sessionContext) {
//		FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<LoginFilter>();
//		LoginFilter loginFilter = new LoginFilter(sessionContext);
//		filterRegistrationBean.setFilter(loginFilter);
//		filterRegistrationBean.addUrlPatterns("/api/*");
//		return filterRegistrationBean;
//	}
}
