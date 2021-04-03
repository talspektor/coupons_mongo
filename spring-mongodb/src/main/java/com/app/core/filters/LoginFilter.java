package com.app.core.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.app.core.session.SessionContext;


public class LoginFilter implements Filter {
	
	private SessionContext sessionContext;
	
	public LoginFilter(SessionContext sessionContext) {
		super();
		this.sessionContext = sessionContext;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("doFilter");
		HttpServletRequest req = (HttpServletRequest)request;
		String token = req.getHeader("token");
		System.out.println("token: " + token);

		if(token != null && sessionContext.getSession(token) != null) {
			chain.doFilter(request, response);
			System.out.println("session is good");
			return;
		}
		System.err.println("doFilter fail");
		HttpServletResponse res = (HttpServletResponse) response;
		res.sendError(HttpStatus.UNAUTHORIZED.value(), "you are not logged in");
	}

}
