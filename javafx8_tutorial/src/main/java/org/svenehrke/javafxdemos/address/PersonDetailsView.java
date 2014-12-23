package org.svenehrke.javafxdemos.address;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.svenehrke.javafxdemos.address.model.Person;

public class PersonDetailsView {

	@FXML
	TableView<Person> personTable;

	@FXML
	TableColumn<Person, String> firstNameColumn;
	@FXML
	TableColumn<Person, String> lastNameColumn;

	@FXML
	Label firstNameLabel;
	@FXML
	Label lastNameLabel;
	@FXML
	Label streetLabel;
	@FXML
	Label postalCodeLabel;
	@FXML
	Label cityLabel;
	@FXML
	Label birthdayLabel;

	@FXML
	Button newButton;

	@FXML
	Button editButton;

	@FXML
	Button deleteButton;
}
