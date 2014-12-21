package org.svenehrke.javafxdemos.address.commandhandler;

import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import org.svenehrke.javafxdemos.address.Model;

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
			boolean okClicked = PersonDialogs.showPersonEditDialog(model1.getWorkPerson(), primaryStage1);
			model1.editOkButtonClicked.setValue(okClicked);
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
