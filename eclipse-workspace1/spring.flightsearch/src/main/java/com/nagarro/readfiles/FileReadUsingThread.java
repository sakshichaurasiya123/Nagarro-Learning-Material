/*

* Description:-  make a FlightReadUsingThread Class to read a data from csv file using thread
*/


package com.nagarro.readfiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.service.FlightService;


public class FileReadUsingThread {
	
	@Autowired
	private static FlightService flightService;
		static final String CSVFILENAME = "C:\\Users\\sakshichaurasiya\\eclipse-workspace1\\spring.flightsearch\\src\\main\\resources\\CSV";
		public static List<String> filename;
		static int count = 0;
		private static List<String> oldFileNames = new ArrayList<String>();

		public static void addcsvfiles() {
		
			filename = new ArrayList<String>();

			String directoryPath = new File(CSVFILENAME).getAbsolutePath();
			File[] filesInDirectory = new File(directoryPath).listFiles();

			for (File f : filesInDirectory) {
				String filePath = f.getAbsolutePath();
				String fileExtenstion = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
				if ("csv".equals(fileExtenstion)) {
					filename.add(f.getName());
					
				}
			}
			
		}

		public static String get_absolute_path() {
			return new File(CSVFILENAME).getAbsolutePath();
		}



}
