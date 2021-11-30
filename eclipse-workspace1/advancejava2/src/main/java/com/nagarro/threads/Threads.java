package com.nagarro.threads;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.nagarro.flightHandle.FlightHandler;
import com.nagarro.readfile.ReadFile;

public class Threads {

	static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

	public static void startThread() {

		Runnable runnable = new Runnable() {

			public void run() {
				// TODO Auto-generated method stub

				ReadFile.files_pass();
				FlightHandler.searchFlights();

			}

		};

		scheduledExecutorService.scheduleAtFixedRate(runnable, 0, 12, TimeUnit.HOURS);

	}

	public static void stopThread() {

		scheduledExecutorService.shutdown();
	}

}
