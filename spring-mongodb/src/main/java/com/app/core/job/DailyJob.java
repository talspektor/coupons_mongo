package com.app.core.job;

import java.sql.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.app.core.repository.CouponRepository;

@Component
public class DailyJob implements Runnable {
	
	@Autowired
	private CouponRepository couponRepository;
	private boolean quit;
	private final int daysInMilliseconds = 1000*60*60*24;
	private Thread jobThread;

	public DailyJob() {
		super();
		this.jobThread = new Thread(this, "job");
	}
	
	@PostConstruct
	public void startJob() {
		this.jobThread.start();
		System.out.println("job started");
	}
	
	@Override
	@Transactional
	public void run() {
		jobThread = Thread.currentThread();
		try {
			System.out.println("CouponExpirationDailyJob run");
			while (!quit) {
				Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
				couponRepository.removeByEndDateLessThan(sqlDate);
				System.out.println("CouponExpirationDailyJob deleteExpierdCoupons");
				Thread.sleep(daysInMilliseconds);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getCause());
		} catch (InterruptedException e) {
			System.out.println("Thread was interupted and will stoped");
		}
		System.out.println("job thread finished");
	}
	
	@PreDestroy
	public void stop() {
		this.quit = true;
		jobThread.interrupt();
		System.out.println("Job stop");
	}
}
