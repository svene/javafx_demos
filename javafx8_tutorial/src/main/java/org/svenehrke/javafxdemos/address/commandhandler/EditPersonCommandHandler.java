package org.svenehrke.javafxdemos.address.commandhandler;

import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import org.svenehrke.javafxdemos.address.Model;

/**
 * Opens a dialog to edit details for the specified person. If the user
 * clicks OK, the changes are saved into the provided person object and true
 * is returned.
 */
public class EditPersonCommandHandler implements Runnable {

	private final Stage primaryStage;
	private final Model model;

	public EditPersonCommandHandler(Stage primaryStage, Model model) {
		this.primaryStage = primaryStage;
		this.model = model;
	}

	@Override
	public void run() {
		handleEditPerson(primaryStage, model);
	}

	private static void handleEditPerson(Stage primaryStage1, Model model1) {
		if (model1.currentPerson != null) {
			model1.getWorkPerson().populateFromPerson(model1.currentPerson);
			PersonDialogs.showPersonDialog(model1, model1.editOkButtonClicked, primaryStage1);
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


}
