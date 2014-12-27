package org.svenehrke.javafxdemos.address;

import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import org.svenehrke.javafxdemos.address.model.Person;
import org.svenehrke.javafxdemos.address.model.PersonListWrapper;
import org.svenehrke.javafxdemos.address.model.Persons;
import org.svenehrke.javafxdemos.infra.ModelStore;
import org.svenehrke.javafxdemos.infra.PresentationModel;
import org.svenehrke.javafxdemos.infra.ViewAndRoot;
import org.svenehrke.javafxdemos.infra.FXMLLoader2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class RootLayoutViewBinder {

	AddressFileHelper addressFileHelper = new AddressFileHelper();

	public void bindView(RootLayoutView view, Model model, ModelStore modelStore) {

		view.miNew.setOnAction(event -> handleNewAddressBookRequest(model, modelStore));
		view.miOpen.setOnAction(event -> handleOpenFileRequest(model, modelStore));
		view.miSave.setOnAction(event -> handleSaveRequest(model) );
		view.miSaveAs.setOnAction(event -> handleSaveAs(model) );
		view.miShowStatistics.setOnAction(event -> showBirthdayStatistics(model));
	}

	private void handleNewAddressBookRequest(Model model, ModelStore modelStore) {
		modelStore.clear();
		//todo: addressFileHelper.setPersonFilePath(null, model.applicationTitle, modelStore);
	}

	/**
	 * Opens a FileChooser to let the user select an address book to load.
	 */
	private void handleOpenFileRequest(Model model, ModelStore modelStore) {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));

		// Show save file dialog
		File file = fileChooser.showOpenDialog(model.getPrimaryStage());

		if (file != null) {
			model.clear(modelStore);
			addressFileHelper.loadPersonDataFromFile(file, model.applicationTitle, modelStore);
		}
	}

	private void handleSaveRequest(Model model) {
		File personFile = addressFileHelper.getPersonFilePath();
		if (personFile != null) {
			savePersonDataToFile(personFile, model.realPresentationModels, model.applicationTitle);
		} else {
			handleSaveAs(model);
		}
	}

	/**
	 * Saves the current person data to the specified file.
	 *
	 * @param file
	 * @param pms
	 * @param applicationTitle
	 */
	private void savePersonDataToFile(File file, List<PresentationModel> pms, StringProperty applicationTitle) {
		try {
			JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Wrapping our person data.
			PersonListWrapper wrapper = new PersonListWrapper();
			List<Person> people = pms.stream().map(Persons::fromPM).collect(toList());
			wrapper.setPersons(people);

			// Marshalling and saving XML to the file.
			m.marshal(wrapper, file);

			// Save the file path to the registry.
			new AddressFileHelper().setPersonFilePath(file, applicationTitle);
		} catch (Exception e) { // catches ANY exception
			e.printStackTrace();
			Dialogs.create().title("Error")
				.masthead("Could not save data to file:\n" + file.getPath())
				.showException(e);
		}
	}

	/**
	 * Opens a FileChooser to let the user select a file to save to.
	 * @param model
	 */
	private void handleSaveAs(Model model) {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
			"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(model.getPrimaryStage());

		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			savePersonDataToFile(file, model.realPresentationModels, model.applicationTitle);
		}
	}

	/**
	 * Opens a dialog to show birthday statistics.
	 * @param model
	 */
	public void showBirthdayStatistics(Model model) {
		final ViewAndRoot<BirthdayStatisticsView, Pane> cr = FXMLLoader2.loadFXML("/BirthdayStatistics.fxml");
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Birthday Statistics");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(model.getPrimaryStage());
		Scene scene = new Scene(cr.getRoot());
		dialogStage.setScene(scene);

		// Set the persons into the view.
		BirthdayStatisticsView view = cr.getView();
		view.setPersonData(null);

		dialogStage.show();

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
