package com.nagarro.validDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValidDateFormat {
	public static boolean isvalid(String date) {
		try {
			new SimpleDateFormat("dd-MM-yyyy").parse(date);
			return true;
		}

		catch (ParseException e) {
			return false;
		}
	}
}
