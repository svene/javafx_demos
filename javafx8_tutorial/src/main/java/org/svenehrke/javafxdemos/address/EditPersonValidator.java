package org.svenehrke.javafxdemos.address;

import org.svenehrke.javafxdemos.address.model.Person;
import org.svenehrke.javafxdemos.address.util.DateUtil;
import org.svenehrke.javafxdemos.infra.PresentationModel;

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

	private void validate(PresentationModel personPM) {
		String errorMessage = "";

		String value = personPM.getAttribute(Person.ATT_FIRST_NAME).getValue();
		if (value == null || value.length() == 0) {
			errorMessage += "No valid first name!\n";
		}

		value = personPM.getAttribute(Person.ATT_FIRST_NAME).getValue();
		if (value == null || value.length() == 0) {
			errorMessage += "No valid last name!\n";
		}
		value = personPM.getAttribute(Person.ATT_STREET).getValue();
		if (value == null || value.length() == 0) {
			errorMessage += "No valid street!\n";
		}

		value = personPM.getAttribute(Person.ATT_POSTAL_CODE).getValue();
		if (value == null || value.length() == 0) {
			errorMessage += "No valid postal code!\n";
		} else {
			// try to parse the postal code into an int.
			try {
				Integer.parseInt(value);
			} catch (NumberFormatException e) {
				errorMessage += "No valid postal code (must be an integer)!\n";
			}
		}

		value = personPM.getAttribute(Person.ATT_CITY).getValue();
		if (value == null || value.length() == 0) {
			errorMessage += "No valid city!\n";
		}

		value = personPM.getAttribute(Person.ATT_BIRTHDAY).getValue();
		if (value == null || value.length() == 0) {
			errorMessage += "No valid birthday!\n";
		} else {
			if (!DateUtil.validDate(value)) {
				errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
			}
		}

		model.validationMessage.setValue(errorMessage);
	}

}
