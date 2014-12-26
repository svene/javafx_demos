package org.svenehrke.javafxdemos.address;

import javafx.stage.Stage;
import org.svenehrke.javafxdemos.address.model.PersonAPI;

public class PersonEditViewBinder {

	public static void bindView(PersonEditDialogView view, Stage dialogStage, Model model) {

		// Bind widgets and workPerson:
		view.firstNameField.textProperty().bindBidirectional(model.workPerson.getAttribute(PersonAPI.ATT_FIRST_NAME).getValueProperty());
		view.lastNameField.textProperty().bindBidirectional(model.workPerson.getAttribute(PersonAPI.ATT_LAST_NAME).getValueProperty());
		view.streetField.textProperty().bindBidirectional(model.workPerson.getAttribute(PersonAPI.ATT_STREET).getValueProperty());
		view.postalCodeField.textProperty().bindBidirectional(model.workPerson.getAttribute(PersonAPI.ATT_POSTAL_CODE).getValueProperty());
		view.cityField.textProperty().bindBidirectional(model.workPerson.getAttribute(PersonAPI.ATT_CITY).getValueProperty());
		view.birthdayField.textProperty().bindBidirectional(model.workPerson.getAttribute(PersonAPI.ATT_BIRTHDAY).getValueProperty());

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
