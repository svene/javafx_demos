package org.svenehrke.javafxdemos.address.commandhandler;

import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import org.svenehrke.javafxdemos.address.Model;
import org.svenehrke.javafxdemos.address.model.Person;

import java.util.function.Consumer;

public class EditPersonCommandHandler implements Runnable {

	private final Stage primaryStage;
	private final Model model;
	private final Consumer<Person> okButtonHandler;

	public EditPersonCommandHandler(Stage primaryStage, Model model, Consumer<Person> okButtonHandler) {
		this.primaryStage = primaryStage;
		this.model = model;
		this.okButtonHandler = okButtonHandler;
	}

	@Override
	public void run() {
		handleEditPerson(primaryStage, model, okButtonHandler);
	}

	private static void handleEditPerson(Stage primaryStage1, Model model1, Consumer<Person> okButtonHandler) {
		Person selectedPerson = model1.getPersonData().get(model1.selectedModelIndex.intValue());
		if (selectedPerson != null) {
			boolean okClicked = PersonDialogs.showPersonEditDialog(selectedPerson, primaryStage1);
			if (okClicked) {
				okButtonHandler.accept(selectedPerson);
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
