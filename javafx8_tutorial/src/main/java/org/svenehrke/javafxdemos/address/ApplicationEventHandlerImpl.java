package org.svenehrke.javafxdemos.address;

import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import org.svenehrke.javafxdemos.address.commandhandler.EditPersonCommandHandler;
import org.svenehrke.javafxdemos.address.commandhandler.NewPersonCommandHandler;

public class ApplicationEventHandlerImpl implements IApplicationEventHandler {

	private final Model model;
	private PersonOverviewController personOverviewController;

	private final NewPersonCommandHandler newPersonCommandHandler;
	private final EditPersonCommandHandler editPersonCommandHandler;

	public ApplicationEventHandlerImpl(Model model, Stage primaryStage, PersonOverviewController personOverviewController) {
		this.model = model;
		this.personOverviewController = personOverviewController;

		newPersonCommandHandler = new NewPersonCommandHandler(primaryStage, model);
		editPersonCommandHandler = new EditPersonCommandHandler(primaryStage, model, personOverviewController);
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
				editPersonCommandHandler.run();
				break;
			case Api.CMD_DELETE:
				handleDelete();
				break;
			default:
				System.out.println("command '" + commandName + "' unknown");
				break;
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
