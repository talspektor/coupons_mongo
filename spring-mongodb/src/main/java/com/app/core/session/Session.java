package com.app.core.session;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Session {

	private Map<String, Object> attributes = new HashMap<String, Object>();
	public String token;
	private long lastAccessed;
	@Value("${session.max.inactive.interval:5}")
	private long maxInactiveInterval; // milliseconds
	private static final int TOKEN_MAX_LENGTH = 15;
	
	{
		this.token = UUID.randomUUID().toString().replace("-", "").substring(TOKEN_MAX_LENGTH);
		this.resetLastAccessed();
	}
	
	public void resetLastAccessed() {
		this.lastAccessed = System.currentTimeMillis();
	}
	
	@PostConstruct
	private void init() {
		maxInactiveInterval = TimeUnit.MINUTES.toMillis(TOKEN_MAX_LENGTH);
	}
	
	public void setAttribute(String attrName, Object attrVal) {
		attributes.put(attrName, attrVal);
	}
	
	public Object getAttritutes(String attrName) {
		return attributes.get(attrName);
	}
	
	public long getMaxInactiveInterval() {
		return maxInactiveInterval;
	}
	
	public long getLastAccessed() {
		return lastAccessed;
	}
}
