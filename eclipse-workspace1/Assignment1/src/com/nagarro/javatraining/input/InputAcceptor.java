package com.nagarro.javatraining.input;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import com.nagarro.javatraining.model.Constants;
import com.nagarro.javatraining.model.UserInput;
public class InputAcceptor implements Constants {

	public static UserInput enterInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String source, destination, flightClass;
		int outputPreference;
		Date flightDate = null;

		System.out.print("DEPARTURE LOCATION : ");

		while ((source = UserInputValidator.validateSource(br.readLine())) == null) {
			continue;
		}

		System.out.print("ARRIVAL LOCATION : ");
		while ((destination = UserInputValidator.validateDestination(br.readLine())) == null) {
			continue;
		}

		System.out.print("Please enter Flight class 'E' for economic and 'EB' for Business : ");
		while ((flightClass = UserInputValidator.validateFlightClass(br.readLine())) == null) {
			continue;
		}

		System.out.print("Date Of Travel(DD-MM-YYYY): ");
		while ((flightDate = UserInputValidator.validateDate(br.readLine())) == null) {
			continue;
		}

		System.out.print("Please enter output preference '1' for fare and '2' for both fare and flight duration : ");
		while ((outputPreference = UserInputValidator
				.validateOutputPreference(Integer.parseInt(br.readLine()))) == 0) {
			continue;
		}

		return new UserInput(source, destination, flightDate,
				flightClass, outputPreference);
	}
}
