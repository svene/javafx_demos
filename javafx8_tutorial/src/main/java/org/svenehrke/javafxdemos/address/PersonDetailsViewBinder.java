package org.svenehrke.javafxdemos.address;

import javafx.beans.binding.Bindings;
import org.svenehrke.javafxdemos.address.commandhandler.DeletePersonCommandHandler;
import org.svenehrke.javafxdemos.address.commandhandler.EditPersonCommandHandler;
import org.svenehrke.javafxdemos.address.commandhandler.NewPersonCommandHandler;
import org.svenehrke.javafxdemos.address.model.Person;
import org.svenehrke.javafxdemos.infra.Mate;

public class PersonDetailsViewBinder {

	public static void bindController(PersonDetailsView view, Mate mate) {
		// Initialize the person table with the two columns.
		view.firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		view.lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

		view.newButton.setOnAction(event -> new NewPersonCommandHandler(mate.getPrimaryStage(), mate.getModel()).run());
		view.editButton.setOnAction(event -> new EditPersonCommandHandler(mate.getPrimaryStage(), mate.getModel()).run());
		view.deleteButton.setOnAction(event -> new DeletePersonCommandHandler(mate.getModel()).run());

		Person cp = mate.getModel().currentPerson;
		view.firstNameLabel.textProperty().bind(cp.firstNameProperty());
		view.lastNameLabel.textProperty().bind(cp.lastNameProperty());
		view.streetLabel.textProperty().bind(cp.streetProperty());
		view.postalCodeLabel.textProperty().bind(Bindings.convert(cp.postalCodeProperty()));
		view.cityLabel.textProperty().bind(cp.cityProperty());
		view.birthdayLabel.textProperty().bind(Bindings.convert(cp.birthdayProperty()));

		// When model.selectedModelIndex changes: change selected row of table:
		mate.getModel().selectedModelIndex.addListener((s,o,n) -> {
			view.personTable.getSelectionModel().select(mate.getModel().selectedModelIndex.intValue());
		});

		view.personTable.setItems(mate.getModel().getPersonData());
		view.personTable.getSelectionModel().selectedIndexProperty().addListener((s, o, n) -> mate.getModel().selectedModelIndex.setValue(n));

	}
}
