package org.svenehrke.javafxdemos.address;

import javafx.fxml.FXML;
import org.svenehrke.javafxdemos.address.commandhandler.DeletePersonCommandHandler;
import org.svenehrke.javafxdemos.address.commandhandler.EditPersonCommandHandler;
import org.svenehrke.javafxdemos.address.commandhandler.NewPersonCommandHandler;
import org.svenehrke.javafxdemos.address.model.Person;
import org.svenehrke.javafxdemos.address.util.DateUtil;
import org.svenehrke.javafxdemos.infra.Mate;

public class PersonOverviewController extends AbstractPersonOverviewController {

	private Mate mate;

	private NewPersonCommandHandler newPersonCommandHandler;
	private EditPersonCommandHandler editPersonCommandHandler;
	private DeletePersonCommandHandler deletePersonCommandHandler;


	public void postConstruct(Mate mate) {
		this.mate = mate;
		newPersonCommandHandler = new NewPersonCommandHandler(mate.getPrimaryStage(), mate.getModel());
		editPersonCommandHandler = new EditPersonCommandHandler(mate.getPrimaryStage(), mate.getModel(), this::populateFromPerson);
		deletePersonCommandHandler = new DeletePersonCommandHandler(mate.getModel());
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

		newButton.setOnAction(event -> newPersonCommandHandler.run());
		editButton.setOnAction(event -> editPersonCommandHandler.run());
		deleteButton.setOnAction(event -> deletePersonCommandHandler.run());

		populateFromPerson(null);
	}

	public void initData() {

		personTable.setItems(mate.getModel().getPersonData());

		personTable.getSelectionModel().selectedIndexProperty().addListener((s, o, n) -> mate.getModel().selectedModelIndex.setValue(n));
		mate.getModel().selectedModelIndex.addListener((s, o, n) -> {
			int index = n.intValue();
			if (index >= 0) {
				populateFromPerson(mate.getModel().getPersonData().get(index));
			}
		});
	}


	/**
	 * Fills all text fields to show details about the person.
	 * If the specified person is null, all text fields are cleared.
	 *
	 * @param person the person or null
	 */
	public void populateFromPerson(Person person) {
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
