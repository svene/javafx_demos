package org.svenehrke.javafxdemos.address;

import javafx.beans.binding.Bindings;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.address.model.Person;
import org.svenehrke.javafxdemos.address.util.LocalDateStringConverter;
import org.svenehrke.javafxdemos.address.util.SimpleNumberStringConverter;

public class PersonEditViewBinder {

	public static void bindController(PersonEditDialogView view, Stage dialogStage, Model model, Person workPerson) {

		// Bind widgets and workPerson:
		view.firstNameField.textProperty().bindBidirectional(workPerson.firstNameProperty());
		view.lastNameField.textProperty().bindBidirectional(workPerson.lastNameProperty());
		view.streetField.textProperty().bindBidirectional(workPerson.streetProperty());
		//Bindings.bindBidirectional(view.postalCodeField.textProperty(), workPerson.postalCodeProperty(), new SimpleNumberStringConverter());
		view.postalCodeField.textProperty().bindBidirectional(workPerson.postalCodeProperty());
		view.cityField.textProperty().bindBidirectional(workPerson.cityProperty());
		Bindings.bindBidirectional(view.birthdayField.textProperty(), workPerson.birthdayProperty(), new LocalDateStringConverter());

		// Bind buttons to actions:
		view.okButton.setOnAction(event -> {
			if (new EditPersonValidator(model).isInputValid(view.asPersonPO())) {
				model.okButtonClicked.setValue(true);
				dialogStage.close();
			}
		});

		view.cancelButton.setOnAction( event -> dialogStage.close() );
	}
}
