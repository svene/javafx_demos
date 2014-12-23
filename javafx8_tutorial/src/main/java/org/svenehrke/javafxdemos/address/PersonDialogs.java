package org.svenehrke.javafxdemos.address;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.address.model.Person;
import org.svenehrke.javafxdemos.address.util.LocalDateStringConverter;
import org.svenehrke.javafxdemos.address.util.SimpleNumberStringConverter;

import java.io.IOException;
import java.net.URL;

public class PersonDialogs {

	public static void showPersonDialog(Model model1, Stage primaryStage1) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			URL resource = Main.class.getResource("/PersonEditDialog.fxml");
			final FXMLLoader loader = new FXMLLoader(resource, null);
			loader.setControllerFactory((Class<?> c) -> {
				PersonEditDialogController controller = new PersonEditDialogController();
				return controller;
			});
			AnchorPane page = loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Person");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage1);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			PersonEditDialogController controller = loader.getController();
			bindController(controller, dialogStage, model1, model1.workPerson); // todo: blog about this pattern: bindController (binding ...) not inside controller but outside of it. Similar to binding of PMs to widgets: not in view but outside

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void bindController(PersonEditDialogController controller, Stage dialogStage, Model model1, Person workPerson) {

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
