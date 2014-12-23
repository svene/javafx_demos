package org.svenehrke.javafxdemos.address;

import org.svenehrke.javafxdemos.address.util.DateUtil;

public class EditPersonValidator {

	/**
	 * Validates the user input
	 *
	 * @return true if the personPO is valid
	 */
	boolean isInputValid(PersonPO personPO) {
		String errorMessage = "";

		if (personPO.firstName == null || personPO.firstName.length() == 0) {
			errorMessage += "No valid first name!\n";
		}
		if (personPO.lastName == null || personPO.lastName.length() == 0) {
			errorMessage += "No valid last name!\n";
		}
		if (personPO.street == null || personPO.street.length() == 0) {
			errorMessage += "No valid street!\n";
		}

		if (personPO.postalCode == null || personPO.postalCode.length() == 0) {
			errorMessage += "No valid postal code!\n";
		} else {
			// try to parse the postal code into an int.
			try {
				Integer.parseInt(personPO.postalCode);
			} catch (NumberFormatException e) {
				errorMessage += "No valid postal code (must be an integer)!\n";
			}
		}

		if (personPO.city == null || personPO.city.length() == 0) {
			errorMessage += "No valid city!\n";
		}

		if (personPO.birthday == null || personPO.birthday.length() == 0) {
			errorMessage += "No valid birthday!\n";
		} else {
			if (!DateUtil.validDate(personPO.birthday)) {
				errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
			}
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			System.out.println("ERROR: " + errorMessage);
			return false;
		}
	}

}
