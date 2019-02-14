package com.soumyadeep.microservices.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulingMethods {

	/*
	 * The following is a sample code that shows how to execute the task every
	 * minute starting at 9:00 AM and ending at 9:59 AM, every day
	 */
	@Scheduled(cron = "0 * 9 * * ?")
	public void cronScheduler() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date date = new Date();

		String strDate = sdf.format(date);
		System.out.println("Java cron job expression:: " + strDate);
	}

	/*
	 * A sample code for executing a task on every second from the application
	 * startup is shown here
	 */
	@Scheduled(fixedRate = 1000)
	public void fixedRateScheduler() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date date = new Date();

		String strDate = sdf.format(date);
		System.out.println("Fixed Rate scheduler:: " + strDate);
	}

	/*
	 * The initialDelay is the time after which the task will be executed the first
	 * time after the initial delay value. An example to execute the task for every
	 * second after 3 seconds from the application startup has been completed is
	 * shown below
	 */
	@Scheduled(fixedDelay = 1000, initialDelay = 3000)
	public void fixedDelayScheduler() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date date = new Date();

		String strDate = sdf.format(date);
		System.out.println("Fixed Delay scheduler:: " + strDate);
	}
}
