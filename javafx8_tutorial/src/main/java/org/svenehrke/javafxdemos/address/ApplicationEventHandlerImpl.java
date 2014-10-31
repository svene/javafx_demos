package org.svenehrke.javafxdemos.address;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.address.model.Person;

import java.io.IOException;
import java.net.URL;

public class ApplicationEventHandlerImpl implements IApplicationEventHandler {

	private final Model model;
	private final Stage primaryStage;
	private PersonOverviewController personOverviewController;

	public ApplicationEventHandlerImpl(Model model, Stage primaryStage, PersonOverviewController personOverviewController) {
		this.model = model;
		this.primaryStage = primaryStage;
		this.personOverviewController = personOverviewController;
	}

	public void setPersonOverviewController(PersonOverviewController personOverviewController) {
		this.personOverviewController = personOverviewController;
	}

	@Override
	public void handleCommand(String commandName) {
		switch (commandName) {
			case Api.CMD_NEW:
				handleNewPerson();
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

	private void handleNewPerson() {
		Person tempPerson = new Person();
		boolean okClicked = showPersonEditDialog(tempPerson);
		if (okClicked) {
			model.getPersonData().add(tempPerson);
		}
	}

	private void handleEditPerson() {
		Person selectedPerson = model.getPersonData().get(model.selectedModelIndex.intValue());
		if (selectedPerson != null) {
			boolean okClicked = showPersonEditDialog(selectedPerson);
			if (okClicked) {
				personOverviewController.showPersonDetails(selectedPerson);
			}

		} else {
			// Nothing selected.
			System.out.println("nothing selected");
//			Dialogs.create()
//				.title("No Selection")
//				.masthead("No Person Selected")
//				.message("Please select a person in the table.")
//				.showWarning();
		}
	}
	private void handleDelete() {
		model.getPersonData().remove(model.selectedModelIndex.intValue());
	}

	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 *
	 * @param person the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showPersonEditDialog(Person person) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			URL resource = Main.class.getResource("/PersonEditDialog.fxml");
			final FXMLLoader loader = new FXMLLoader(resource, null);
			AnchorPane page = loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Person");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			PersonEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPerson(person);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}


}
