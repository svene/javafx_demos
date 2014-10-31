package org.svenehrke.javafxdemos.address;

import javafx.fxml.FXML;
import org.svenehrke.javafxdemos.address.model.Person;
import org.svenehrke.javafxdemos.address.util.DateUtil;

public class PersonOverviewController extends AbstractPersonOverviewController {

	private IApplicationEventHandler applicationEventHandler;
	private Model model;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstName);
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastName);

		showPersonDetails(null);

		newButton.setOnAction(event -> applicationEventHandler.handleCommand(Api.CMD_NEW));
		editButton.setOnAction(event -> applicationEventHandler.handleCommand(Api.CMD_EDIT));
		deleteButton.setOnAction(event -> applicationEventHandler.handleCommand(Api.CMD_DELETE));
	}

	public void setApplicationEventHandler(IApplicationEventHandler applicationEventHandler) {
		this.applicationEventHandler = applicationEventHandler;
	}

	public void setModel(Model model) {

		this.model = model;
		personTable.setItems(model.getPersonData());

		personTable.getSelectionModel().selectedIndexProperty().addListener((s, o, n) -> model.selectedModelIndex.setValue(n));
		model.selectedModelIndex.addListener((s,o,n) -> showPersonDetails(model.getPersonData().get(n.intValue())));
	}


	/**
	 * Fills all text fields to show details about the person.
	 * If the specified person is null, all text fields are cleared.
	 *
	 * @param person the person or null
	 */
	private void showPersonDetails(Person person) {
		if (person != null) {
			// Fill the labels with info from the person object.
			firstNameLabel.setText(person.firstName.getValue());
			lastNameLabel.setText(person.lastName.getValue());
			streetLabel.setText(person.street.getValue());
			postalCodeLabel.setText(Integer.toString(person.postalCode.getValue()));
			cityLabel.setText(person.city.getValue());

			birthdayLabel.setText(DateUtil.format(person.birthday.getValue()));
		} else {
			// Person is null, remove all the text.
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			postalCodeLabel.setText("");
			cityLabel.setText("");
			birthdayLabel.setText("");
		}
	}
}
