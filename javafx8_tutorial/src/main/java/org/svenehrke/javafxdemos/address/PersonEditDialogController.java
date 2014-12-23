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

	public PersonPO asPersonPO() {
		PersonPO result = new PersonPO();
		result.firstName = firstNameField.getText();
		result.lastName = lastNameField.getText();
		result.street = streetField.getText();
		result.postalCode = postalCodeField.getText();
		result.city = cityField.getText();
		result.birthday = birthdayField.getText();

		return result;
	}

}