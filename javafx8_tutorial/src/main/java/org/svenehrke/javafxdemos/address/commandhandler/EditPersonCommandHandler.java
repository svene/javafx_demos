package org.svenehrke.javafxdemos.address.commandhandler;

import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import org.svenehrke.javafxdemos.address.Model;
import org.svenehrke.javafxdemos.address.PersonOverviewController;
import org.svenehrke.javafxdemos.address.model.Person;

public class EditPersonCommandHandler implements Runnable {

	private final Stage primaryStage;
	private final Model model;
	private final PersonOverviewController personOverviewController;

	public EditPersonCommandHandler(Stage primaryStage, Model model, PersonOverviewController personOverviewController) {
		this.primaryStage = primaryStage;
		this.model = model;
		this.personOverviewController = personOverviewController;
	}

	@Override
	public void run() {
		handleEditPerson(primaryStage, model, personOverviewController);
	}

	private static void handleEditPerson(Stage primaryStage1, Model model1, PersonOverviewController personOverviewController1) {
		Person selectedPerson = model1.getPersonData().get(model1.selectedModelIndex.intValue());
		if (selectedPerson != null) {
			boolean okClicked = PersonDialogs.showPersonEditDialog(selectedPerson, primaryStage1);
			if (okClicked) {
				personOverviewController1.populateFromPerson(selectedPerson);//todo: remove dependency
			}

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
