package org.svenehrke.javafxdemos.address;

import javafx.stage.Stage;
import org.svenehrke.javafxdemos.address.model.Person;

public class PersonEditViewBinder {

	// todo: date format incorrect

	public static void bindController(PersonEditDialogView view, Stage dialogStage, Model model, Person workPerson) {

		// Bind widgets and workPerson:
		view.firstNameField.textProperty().bindBidirectional(workPerson.firstNameProperty());
		view.lastNameField.textProperty().bindBidirectional(workPerson.lastNameProperty());
		view.streetField.textProperty().bindBidirectional(workPerson.streetProperty());
		view.postalCodeField.textProperty().bindBidirectional(workPerson.postalCodeProperty());
		view.cityField.textProperty().bindBidirectional(workPerson.cityProperty());
		view.birthdayField.textProperty().bindBidirectional(workPerson.birthdayProperty());

		// Bind buttons to actions:
		view.okButton.setOnAction(event -> {
			model.okButtonClicked.setValue(true);
			dialogStage.close();
		});

		// Enable okButton when workPerson is valid:
		view.okButton.disableProperty().bind(model.workPersonValid.not());

		view.cancelButton.setOnAction( event -> dialogStage.close() );
	}
}
