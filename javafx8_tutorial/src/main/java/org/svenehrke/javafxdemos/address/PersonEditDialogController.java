package org.svenehrke.javafxdemos.address;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.address.model.Person;
import org.svenehrke.javafxdemos.address.util.DateUtil;

/**
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
 */
public class PersonEditDialogController {

	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField postalCodeField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField birthdayField;

	@FXML
	private Button okButton;
	@FXML
	private Button cancelButton;


	private Stage dialogStage;
	private Person person;
	private boolean okClicked = false;

	public PersonEditDialogController(Person person) {
		this.person = person;
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		firstNameField.setText(person.firstNameProperty().getValue());
		lastNameField.setText(person.lastNameProperty().getValue());
		streetField.setText(person.streetProperty().getValue());
		postalCodeField.setText(Integer.toString(person.postalCodeProperty().getValue()));
		cityField.setText(person.cityProperty().getValue());
		birthdayField.setText(DateUtil.format(person.birthdayProperty().getValue()));
		birthdayField.setPromptText("dd.mm.yyyy");

		okButton.setOnAction(event -> handleOk());
		cancelButton.setOnAction(event -> handleCancel());
	}

	public void postInitialize(Stage dialogStage) {

		this.dialogStage = dialogStage;
	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 *
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			person.firstNameProperty().setValue(firstNameField.getText());
			person.lastNameProperty().setValue(lastNameField.getText());
			person.streetProperty().setValue(streetField.getText());
			person.postalCodeProperty().setValue(Integer.parseInt(postalCodeField.getText()));
			person.cityProperty().setValue(cityField.getText());
			person.birthdayProperty().setValue(DateUtil.parse(birthdayField.getText()));

			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * Validates the user input in the text fields.
	 *
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
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