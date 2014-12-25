package org.svenehrke.javafxdemos.address;

import javafx.beans.binding.Bindings;
import org.controlsfx.dialog.Dialogs;
import org.svenehrke.javafxdemos.address.model.Person;
import org.svenehrke.javafxdemos.infra.ModelStore;

public class PersonDetailsViewBinder {

	public static void bindView(PersonDetailsView view, Model model, ModelStore modelStore) {
		// Initialize the person table with the two columns.
		view.firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		view.lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

		view.newButton.setOnAction(event -> handleNewPerson(model, modelStore));
		view.editButton.setOnAction(event -> handleEditPerson(model) );
		view.deleteButton.setOnAction(event -> handleDelete(model) );

		Person cp = model.currentPerson;
		view.firstNameLabel.textProperty().bind(cp.firstNameProperty());
		view.lastNameLabel.textProperty().bind(cp.lastNameProperty());
		view.streetLabel.textProperty().bind(cp.streetProperty());
		view.postalCodeLabel.textProperty().bind(Bindings.convert(cp.postalCodeProperty()));
		view.cityLabel.textProperty().bind(cp.cityProperty());
		view.birthdayLabel.textProperty().bind(Bindings.convert(cp.birthdayProperty()));

		// When model.selectedModelIndex changes: change selected row of table:
		model.selectedModelIndex.addListener((s,o,n) -> {
			view.personTable.getSelectionModel().select(model.selectedModelIndex.intValue());
		});

		view.personTable.setItems(model.getPeople());
		view.personTable.getSelectionModel().selectedIndexProperty().addListener((s, o, n) -> model.selectedModelIndex.setValue(n));
	}

	private static void handleNewPerson(Model model, ModelStore modelStore) {
		model.workPerson.populateFromPerson(modelStore.newEmptyPerson(), false);
		model.editModeProperty.setValue(Model.EditMode.NEW);
		PersonDialogs.showPersonDialog(model);
	}

	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 */
	private static void handleEditPerson(Model model) {
		if (model.currentPerson != null) {
			model.getWorkPerson().populateFromPerson(model.currentPerson, false);
			model.editModeProperty.setValue(Model.EditMode.EDIT);
			PersonDialogs.showPersonDialog(model);
		} else {
			// Nothing selected.
			System.out.println("nothing selected");
			Dialogs.create()
				.title("No Selection")
				.masthead("No Person Selected")
				.message("Please select a person in the table.")
				.showWarning();
		}
	}

	private static void handleDelete(Model model) {
		int selectedIndex = model.selectedModelIndex.intValue();
		if (selectedIndex >= 0) {
			model.getPeople().remove(selectedIndex);
		} else {
			// Nothing selected.
			Dialogs.create()
				.title("No Selection")
				.masthead("No Person Selected")
				.message("Please select a person in the table.")
				.showWarning();
		}

	}
}