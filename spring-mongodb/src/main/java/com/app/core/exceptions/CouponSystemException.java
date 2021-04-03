package com.app.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CouponSystemException extends ResponseStatusException {

	public CouponSystemException(HttpStatus status, String reason, Throwable cause) {
		super(status, reason, cause);
		// TODO Auto-generated constructor stub
	}

	public CouponSystemException(HttpStatus status, String reason) {
		super(status, reason);
		// TODO Auto-generated constructor stub
	}

	public CouponSystemException(HttpStatus status) {
		super(status);
		// TODO Auto-generated constructor stub
	}

	public CouponSystemException(int rawStatusCode, String reason, Throwable cause) {
		super(rawStatusCode, reason, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	public CouponSystemException() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public CouponSystemException(String message, Throwable cause, boolean enableSuppression,
//			boolean writableStackTrace) {
//		super(message, cause, enableSuppression, writableStackTrace);
//		// TODO Auto-generated constructor stub
//	}
//
//	public CouponSystemException(String message, Throwable cause) {
//		super(message, cause);
//		// TODO Auto-generated constructor stub
//	}
//
//	public CouponSystemException(String message) {
//		super(message);
//		// TODO Auto-generated constructor stub
//	}
//
//	public CouponSystemException(Throwable cause) {
//		super(cause);
//		// TODO Auto-generated constructor stub
//	}

	
}
