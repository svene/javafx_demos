package org.svenehrke.javafxdemos.address;

import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import org.svenehrke.javafxdemos.address.commandhandler.NewPersonCommandHandler;
import org.svenehrke.javafxdemos.address.commandhandler.PersonDialogs;
import org.svenehrke.javafxdemos.address.model.Person;

public class ApplicationEventHandlerImpl implements IApplicationEventHandler {

	private final Model model;
	private final Stage primaryStage;
	private PersonOverviewController personOverviewController;

	private final NewPersonCommandHandler newPersonCommandHandler;

	public ApplicationEventHandlerImpl(Model model, Stage primaryStage, PersonOverviewController personOverviewController) {
		this.model = model;
		this.primaryStage = primaryStage;
		this.personOverviewController = personOverviewController;

		newPersonCommandHandler = new NewPersonCommandHandler(primaryStage, model);
	}

	public void setPersonOverviewController(PersonOverviewController personOverviewController) {
		this.personOverviewController = personOverviewController;
	}

	@Override
	public void handleCommand(String commandName) {
		switch (commandName) {
			case Api.CMD_NEW:
				newPersonCommandHandler.run();
				break;
			case Api.CMD_EDIT:
				handleEditPerson();
				break;
			case Api.CMD_DELETE:
				handleDelete();
				break;
			default:
				System.out.println("command '" + commandName + "' unknown");
				break;
		}
	}

	private void handleEditPerson() {
		Person selectedPerson = model.getPersonData().get(model.selectedModelIndex.intValue());
		if (selectedPerson != null) {
			boolean okClicked = PersonDialogs.showPersonEditDialog(selectedPerson, primaryStage);
			if (okClicked) {
				personOverviewController.showPersonDetails(selectedPerson);
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
	private void handleDelete() {
		int selectedIndex = model.selectedModelIndex.intValue();
		if (selectedIndex >= 0) {
			model.getPersonData().remove(selectedIndex);
		} else {
			// Nothing selected.
			Dialogs.create()
				.title("No Selection")
				.masthead("No Person Selected")
				.message("Please select a person in the table.")
				.showWarning();
		}

	}


}
