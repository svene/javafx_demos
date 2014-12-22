package org.svenehrke.javafxdemos.address;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.svenehrke.javafxdemos.address.util.DateUtil;
import org.svenehrke.javafxdemos.address.util.LocalDateStringConverter;
import org.svenehrke.javafxdemos.address.util.SimpleNumberStringConverter;

/**
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
 */
public class PersonEditDialogController {

	@FXML
	TextField firstNameField;
	@FXML
	TextField lastNameField;
	@FXML
	TextField streetField;
	@FXML
	TextField postalCodeField;
	@FXML
	TextField cityField;
	@FXML
	TextField birthdayField;

	@FXML
	Button okButton;
	@FXML
	Button cancelButton;

	private Model model;

	public PersonEditDialogController(Model model) {
		this.model = model;
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		firstNameField.textProperty().bindBidirectional(model.workPerson.firstNameProperty());
		lastNameField.textProperty().bindBidirectional(model.workPerson.lastNameProperty());
		streetField.textProperty().bindBidirectional(model.workPerson.streetProperty());
		Bindings.bindBidirectional(postalCodeField.textProperty(), model.workPerson.postalCodeProperty(), new SimpleNumberStringConverter());
		cityField.textProperty().bindBidirectional(model.workPerson.cityProperty());
		Bindings.bindBidirectional(birthdayField.textProperty(), model.workPerson.birthdayProperty(), new LocalDateStringConverter());
	}

	/**
	 * Validates the user input in the text fields.
	 *
	 * @return true if the input is valid
	 */
	boolean isInputValid() { // todo: move this validation logic outside of GUI into separate validator
		String errorMessage = "";

		if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
			errorMessage += "No valid first name!\n";
		}
		if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
			errorMessage += "No valid last name!\n";
		}
		if (streetField.getText() == null || streetField.getText().length() == 0) {
			errorMessage += "No valid street!\n";
		}

		if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
			errorMessage += "No valid postal code!\n";
		} else {
			// try to parse the postal code into an int.
			try {
				Integer.parseInt(postalCodeField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid postal code (must be an integer)!\n";
			}
		}

		if (cityField.getText() == null || cityField.getText().length() == 0) {
			errorMessage += "No valid city!\n";
		}

		if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
			errorMessage += "No valid birthday!\n";
		} else {
			if (!DateUtil.validDate(birthdayField.getText())) {
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