package org.svenehrke.javafxdemos.address;

import javafx.stage.Stage;

public class PersonEditViewBinder {

	public static void bindView(PersonEditDialogView view, Stage dialogStage, Model model) {

		// Bind widgets and workPerson:
		view.firstNameField.textProperty().bindBidirectional(model.workPerson.firstNameProperty());
		view.lastNameField.textProperty().bindBidirectional(model.workPerson.lastNameProperty());
		view.streetField.textProperty().bindBidirectional(model.workPerson.streetProperty());
		view.postalCodeField.textProperty().bindBidirectional(model.workPerson.postalCodeProperty());
		view.cityField.textProperty().bindBidirectional(model.workPerson.cityProperty());
		view.birthdayField.textProperty().bindBidirectional(model.workPerson.birthdayProperty());

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
