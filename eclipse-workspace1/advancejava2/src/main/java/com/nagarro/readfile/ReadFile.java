package com.nagarro.readfile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nagarro.flightDetails.FlightInfo;

public class ReadFile {

	static final String CSV_FILES = "C:\\Users\\sakshichaurasiya\\eclipse-workspace1\\advancejava2\\CSVFile";
	public static List<String> file_name;
	static int track = 0;

	private static List<String> oldFilenames = new ArrayList<String>();

	public static void files_pass() {

		file_name = new ArrayList<String>();

		String directoryPath = new File(CSV_FILES).getAbsolutePath();
		File[] files = new File(directoryPath).listFiles();

		for (File f : files) {

			String filepath = f.getAbsolutePath();
			String fileExtension = filepath.substring(filepath.lastIndexOf(".") + 1, filepath.length());
			if ("csv".equals(fileExtension)) {

				file_name.add(f.getName());
			}
		}

		getCSVlist();
	}

	public static String getabsolutepath() {

		return new File(CSV_FILES).getAbsolutePath();
	}

	public static void getCSVlist() {

		List<String> copyFile = new ArrayList<String>();
		if (track == 0) {
			removeAll();
			oldFilenames.addAll(file_name);
			track++;
		}

		else {

			copyFile.addAll(file_name);
			file_name.removeAll(oldFilenames);
			oldFilenames.addAll(copyFile);
		}

	}

	public static void removeAll() {

		Configuration config = new Configuration().configure().addAnnotatedClass(FlightInfo.class);
		SessionFactory sessionfactory = config.buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		session.createQuery("delete from FlightInfo").executeUpdate();
		session.getTransaction().commit();
		session.close();
		sessionfactory.close();
	}

}
