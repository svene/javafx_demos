package org.svenehrke.javafxdemos.address;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import org.svenehrke.javafxdemos.address.model.PersonListWrapper;
import org.svenehrke.javafxdemos.infra.Mate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class RootLayoutViewBinder {

	AddressFileHelper addressFileHelper = new AddressFileHelper();

	public void bindView(RootLayoutView view, Mate mate) {

		view.miNew.setOnAction(event -> handleNewAddressBookRequest(mate));
		view.miOpen.setOnAction(event -> handleOpenFileRequest(mate));
		view.miSave.setOnAction(event -> handleSaveRequest(mate) );
		view.miSaveAs.setOnAction(event -> handleSaveAs(mate.getPrimaryStage(), mate.getModel()) );
		view.miShowStatistics.setOnAction(event -> showBirthdayStatistics(mate.getPrimaryStage(), mate.getModel() ));
	}

	private void handleNewAddressBookRequest(Mate mate) {
		mate.getModel().getPeople().clear();
		addressFileHelper.setPersonFilePath(null, mate.getPrimaryStage());
	}

	/**
	 * Opens a FileChooser to let the user select an address book to load.
	 */
	private void handleOpenFileRequest(Mate mate) {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));

		// Show save file dialog
		File file = fileChooser.showOpenDialog(mate.getPrimaryStage());

		if (file != null) {
			addressFileHelper.loadPersonDataFromFile(file, mate.getModel(), mate.getPrimaryStage());
		}
	}

	private void handleSaveRequest(Mate mate) {
		File personFile = addressFileHelper.getPersonFilePath();
		if (personFile != null) {
			savePersonDataToFile(personFile, mate.getPrimaryStage(), mate.getModel());
		} else {
			handleSaveAs(mate.getPrimaryStage(), mate.getModel());
		}
	}

	/**
	 * Saves the current person data to the specified file.
	 *
	 * @param file
	 * @param primaryStage
	 * @param model
	 */
	private void savePersonDataToFile(File file, Stage primaryStage, Model model) {
		try {
			JAXBContext context = JAXBContext
				.newInstance(PersonListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Wrapping our person data.
			PersonListWrapper wrapper = new PersonListWrapper();
			wrapper.setPersons(model.getPeople());

			// Marshalling and saving XML to the file.
			m.marshal(wrapper, file);

			// Save the file path to the registry.
			new AddressFileHelper().setPersonFilePath(file, primaryStage);
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
	private void handleSaveAs(Stage primaryStage, Model model) {
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

	/**
	 * Opens a dialog to show birthday statistics.
	 * @param primaryStage
	 * @param model
	 */
	public void showBirthdayStatistics(Stage primaryStage, Model model) {
		try {
			// Load the fxml file and create a new stage for the popup.
			URL resource = Main.class.getResource("/BirthdayStatistics.fxml");
			final FXMLLoader loader = new FXMLLoader(resource, null);
			AnchorPane page = loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Birthday Statistics");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the persons into the view.
			BirthdayStatisticsView view = loader.getController();
			view.setPersonData(model.getPeople());

			dialogStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens an about dialog.
	 */
	private void handleAbout() {
		Dialogs.create()
			.title("AddressApp")
			.masthead("About")
			.message("Author: Marco Jakob\nWebsite: http://code.makery.ch")
			.showInformation();
	}

	/**
	 * Closes the application.
	 */
	private void handleExit() {
		System.out.println("exit");
		System.exit(0);
	}

}
