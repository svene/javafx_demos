package org.svenehrke.javafxdemos.address;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.svenehrke.javafxdemos.address.commandhandler.DeletePersonCommandHandler;
import org.svenehrke.javafxdemos.address.commandhandler.EditPersonCommandHandler;
import org.svenehrke.javafxdemos.address.commandhandler.NewPersonCommandHandler;
import org.svenehrke.javafxdemos.address.model.Person;
import org.svenehrke.javafxdemos.infra.Mate;

public class PersonOverviewController {

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

	private Mate mate;

	private NewPersonCommandHandler newPersonCommandHandler;
	private EditPersonCommandHandler editPersonCommandHandler;
	private DeletePersonCommandHandler deletePersonCommandHandler;

	public PersonOverviewController(Mate mate) {
		this.mate = mate;
		newPersonCommandHandler = new NewPersonCommandHandler(mate.getPrimaryStage(), mate.getModel());
		editPersonCommandHandler = new EditPersonCommandHandler(mate.getPrimaryStage(), mate.getModel());
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

		Person cp = mate.getModel().currentPerson;
		firstNameLabel.textProperty().bind(cp.firstNameProperty());
		lastNameLabel.textProperty().bind(cp.lastNameProperty());
		streetLabel.textProperty().bind(cp.streetProperty());
		postalCodeLabel.textProperty().bind(Bindings.convert(cp.postalCodeProperty()));
		cityLabel.textProperty().bind(cp.cityProperty());
		birthdayLabel.textProperty().bind(Bindings.convert(cp.birthdayProperty()));

		// When model.selectedModelIndex changes: change selected row of table:
		mate.getModel().selectedModelIndex.addListener((s,o,n) -> {
			personTable.getSelectionModel().select(mate.getModel().selectedModelIndex.intValue());
		});

	}

	public void postInitialize() {

		personTable.setItems(mate.getModel().getPersonData());
		personTable.getSelectionModel().selectedIndexProperty().addListener((s, o, n) -> mate.getModel().selectedModelIndex.setValue(n));
	}

}
