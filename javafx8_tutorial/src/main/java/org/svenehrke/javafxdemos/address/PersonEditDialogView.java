package org.svenehrke.javafxdemos.address;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
 */
public class PersonEditDialogView {

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
}