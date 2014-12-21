package org.svenehrke.javafxdemos.infra;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import org.svenehrke.javafxdemos.address.Model;
import org.svenehrke.javafxdemos.address.model.PersonListWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Saves the file to the person file that is currently open. If there is no
 * open file, the "save as" dialog is shown.
 */
public class SaveCommandHandler implements Runnable {

	private final Stage primaryStage;
	private final Model model;

	public SaveCommandHandler(Stage primaryStage, Model model) {
		this.primaryStage = primaryStage;
		this.model = model;
	}

	@Override
	public void run() {
		File personFile = OpenFileCommandHandler.getPersonFilePath();
		if (personFile != null) {
			savePersonDataToFile(personFile, primaryStage, model);
		} else {
			handleSaveAs(primaryStage, model);
		}
	}

	/**
	 * Saves the current person data to the specified file.
	 *
	 * @param file
	 * @param primaryStage1
	 * @param model1
	 */
	public static void savePersonDataToFile(File file, Stage primaryStage1, Model model1) {
		try {
			JAXBContext context = JAXBContext
				.newInstance(PersonListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Wrapping our person data.
			PersonListWrapper wrapper = new PersonListWrapper();
			wrapper.setPersons(model1.getPersonData());

			// Marshalling and saving XML to the file.
			m.marshal(wrapper, file);

			// Save the file path to the registry.
			OpenFileCommandHandler.setPersonFilePath(file, primaryStage1);
		} catch (Exception e) { // catches ANY exception
			e.printStackTrace();
			Dialogs.create().title("Error")
				.masthead("Could not save data to file:\n" + file.getPath())
				.showException(e);
		}
	}

	/**
	 * Opens a FileChooser to let the user select a file to save to.
	 * @param primaryStage
	 * @param model
	 */
	@FXML
	public static void handleSaveAs(Stage primaryStage, Model model) {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
			"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(primaryStage);

		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			savePersonDataToFile(file, primaryStage, model);
		}
	}
}
