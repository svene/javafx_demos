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
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

		showPersonDetails(null);

	}

	public void setApplicationEventHandler(IApplicationEventHandler applicationEventHandler) {
		this.applicationEventHandler = applicationEventHandler;
		newButton.setOnAction(event -> applicationEventHandler.handleCommand(Api.CMD_NEW));
		editButton.setOnAction(event -> {
			this.applicationEventHandler.handleCommand(Api.CMD_EDIT);
		});
		deleteButton.setOnAction(event -> applicationEventHandler.handleCommand(Api.CMD_DELETE));
	}

	public void setModel(Model model) {

		this.model = model;
		personTable.setItems(model.getPersonData());

		personTable.getSelectionModel().selectedIndexProperty().addListener((s, o, n) -> model.selectedModelIndex.setValue(n));
		model.selectedModelIndex.addListener((s, o, n) -> {
			int index = n.intValue();
			if (index >= 0) {
				showPersonDetails(model.getPersonData().get(index));
			}
		});
	}


	/**
	 * Fills all text fields to show details about the person.
	 * If the specified person is null, all text fields are cleared.
	 *
	 * @param person the person or null
	 */
	public void showPersonDetails(Person person) {
		if (person != null) {
			// Fill the labels with info from the person object.
			firstNameLabel.setText(person.firstNameProperty().getValue());
			lastNameLabel.setText(person.lastNameProperty().getValue());
			streetLabel.setText(person.streetProperty().getValue());
			postalCodeLabel.setText(Integer.toString(person.postalCodeProperty().getValue()));
			cityLabel.setText(person.cityProperty().getValue());

			birthdayLabel.setText(DateUtil.format(person.birthdayProperty().getValue()));
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
