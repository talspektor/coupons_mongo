package com.app.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.core.session.Session;
import com.app.core.session.SessionContext;

@CrossOrigin
@RestController
public class LogoutController {
	@Autowired
	private SessionContext sessionContext;

	@PostMapping("/logout")
	public void logout(@RequestHeader String token) {
		Session session = sessionContext.getSession(token);
		sessionContext.invalidate(session);
	}
}
