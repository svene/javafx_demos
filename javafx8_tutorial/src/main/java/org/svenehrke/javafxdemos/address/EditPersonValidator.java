package org.svenehrke.javafxdemos.address;

import org.svenehrke.javafxdemos.address.model.Person;
import org.svenehrke.javafxdemos.address.util.DateUtil;

// todo: instead of returning true/false use simple model properties for validation message and validation result(true/false) -> GUI can react to it as if it were normal handling
public class EditPersonValidator {

	private final Model model;

	public EditPersonValidator(Model model) {

		this.model = model;
	}

	/**
	 * Validates user input
	 */
	void validate() {
		validate(model.workPerson);
	}

	private void validate(Person person) {
		String errorMessage = "";

		if (person.getFirstName() == null || person.getFirstName().length() == 0) {
			errorMessage += "No valid first name!\n";
		}
		if (person.getLastName() == null || person.getLastName().length() == 0) {
			errorMessage += "No valid last name!\n";
		}
		if (person.getStreet() == null || person.getStreet().length() == 0) {
			errorMessage += "No valid street!\n";
		}

		if (person.postalCodeProperty().get() == null || person.postalCodeProperty().get().length() == 0) {
			errorMessage += "No valid postal code!\n";
		} else {
			// try to parse the postal code into an int.
			try {
				Integer.parseInt(person.postalCodeProperty().get());
			} catch (NumberFormatException e) {
				errorMessage += "No valid postal code (must be an integer)!\n";
			}
		}

		if (person.getCity() == null || person.getCity().length() == 0) {
			errorMessage += "No valid city!\n";
		}

		if (person.birthdayProperty().get() == null || person.birthdayProperty().get().length() == 0) {
			errorMessage += "No valid birthday!\n";
		} else {
			if (!DateUtil.validDate(person.birthdayProperty().get())) {
				errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
			}
		}

		model.validationMessage.setValue(errorMessage);
	}

}
