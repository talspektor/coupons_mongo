package com.app.core.services;

import com.app.core.exceptions.CouponSystemException;

public interface ClientService {
	public boolean login(String email, String password) throws CouponSystemException;
}
