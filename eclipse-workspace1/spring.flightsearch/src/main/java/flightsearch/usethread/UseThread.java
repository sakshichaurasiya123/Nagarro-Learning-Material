/*
* Description:-  make a UseThread Class to make a thread and run a thread in a program.
*/


package flightsearch.usethread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.nagarro.operation.FlightOperation;
import com.nagarro.readfiles.FileReadUsingThread;
import com.nagarro.service.FlightService;

public class UseThread {

		private static final List<String> file = new ArrayList<String>();
		private final static ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

		public void startThread(final FlightService flightService) {
			Runnable runnable = new Runnable() {
				public void run() {
					
					FileReadUsingThread.addcsvfiles();
					
					FlightOperation.addFlight(flightService);
				
					

				}
			};
			service.scheduleAtFixedRate(runnable, 0, 50000, TimeUnit.SECONDS);
		}

		public static void stopThread() {

			service.shutdown();
		}
}
