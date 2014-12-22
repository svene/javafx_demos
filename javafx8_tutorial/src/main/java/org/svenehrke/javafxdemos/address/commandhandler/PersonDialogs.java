package org.svenehrke.javafxdemos.address.commandhandler;

import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.address.Main;
import org.svenehrke.javafxdemos.address.Model;
import org.svenehrke.javafxdemos.address.PersonEditDialogController;

import java.io.IOException;
import java.net.URL;

public class PersonDialogs {

	public static void showPersonDialog(Model model1, BooleanProperty okButtonClickedProperty, Stage primaryStage1) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			URL resource = Main.class.getResource("/PersonEditDialog.fxml");
			final FXMLLoader loader = new FXMLLoader(resource, null);
			loader.setControllerFactory((Class<?> c) -> {
				PersonEditDialogController controller = new PersonEditDialogController(model1);
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
			controller.postInitialize(dialogStage);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			okButtonClickedProperty.setValue(controller.isOkClicked());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
