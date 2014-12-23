package org.svenehrke.javafxdemos.address;

import javafx.beans.binding.Bindings;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.address.model.Person;
import org.svenehrke.javafxdemos.address.util.LocalDateStringConverter;
import org.svenehrke.javafxdemos.address.util.SimpleNumberStringConverter;

public class PersonEditDialogBinder {

	public static void bindController(PersonEditDialogController controller, Stage dialogStage, Model model1, Person workPerson) {

		// Bind widgets and workPerson:
		controller.firstNameField.textProperty().bindBidirectional(workPerson.firstNameProperty());
		controller.lastNameField.textProperty().bindBidirectional(workPerson.lastNameProperty());
		controller.streetField.textProperty().bindBidirectional(workPerson.streetProperty());
		Bindings.bindBidirectional(controller.postalCodeField.textProperty(), workPerson.postalCodeProperty(), new SimpleNumberStringConverter());
		controller.cityField.textProperty().bindBidirectional(workPerson.cityProperty());
		Bindings.bindBidirectional(controller.birthdayField.textProperty(), workPerson.birthdayProperty(), new LocalDateStringConverter());

		// Bind buttons to actions:
		controller.okButton.setOnAction(event -> {
			if (new EditPersonValidator().isInputValid(controller.asPersonPO())) {
				model1.okButtonClicked.setValue(true);
				dialogStage.close();
			}
		});

		controller.cancelButton.setOnAction( event -> dialogStage.close() );
	}
}
