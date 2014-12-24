package org.svenehrke.javafxdemos.address;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.infra.FXMLLoader2;
import org.svenehrke.javafxdemos.infra.ViewAndRoot;

public class PersonDialogs {

	public static void showPersonDialog(Model model) {
		ViewAndRoot<PersonEditDialogView, AnchorPane> viewAndRoot = FXMLLoader2.loadFXML("/PersonEditDialog.fxml");

		// Create the dialog Stage.
		Stage dialogStage = newDialogStage(viewAndRoot.getRoot(), model.getPrimaryStage());

		// Set the person into the view.
		PersonEditDialogView view = viewAndRoot.getView();
		PersonEditViewBinder.bindView(view, dialogStage, model); // todo: blog about this pattern: bindView (binding ...) not inside view but outside of it. Similar to binding of PMs to widgets: not in view but outside

		// Show the dialog and wait until the user closes it
		dialogStage.showAndWait();
	}

	private static Stage newDialogStage(AnchorPane page, Stage primaryStage) {
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Edit Person");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		return dialogStage;
	}

}
