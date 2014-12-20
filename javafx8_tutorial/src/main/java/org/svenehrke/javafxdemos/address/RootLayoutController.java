package org.svenehrke.javafxdemos.address;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import org.controlsfx.dialog.Dialogs;
import org.svenehrke.javafxdemos.infra.Mate;
import org.svenehrke.javafxdemos.infra.OpenFileCommandHandler;

import java.io.File;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 *
 * @author Marco Jakob
 */
public class RootLayoutController {

	// Reference to the main application
	private Main mainApp;
	private Mate mate;

	@FXML
	private MenuItem miNew;
	@FXML
	private MenuItem miOpen;
	@FXML
	private MenuItem miSave;
	@FXML
	private MenuItem miSaveAs;
	@FXML
	private MenuItem miShowStatistics;

	@FXML
	private void initialize() {
		miNew.setOnAction(event -> handleNew());
		miOpen.setOnAction(event -> mate.handleCommand(Api.OPEN_FILE));
		miSave.setOnAction(event -> handleSave());
		miSaveAs.setOnAction(event -> handleSaveAs());
		miShowStatistics.setOnAction(event -> handleShowBirthdayStatistics());
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 * @param mate
	 */
	public void setMainApp(Main mainApp, Mate mate) {
		this.mainApp = mainApp;
		this.mate = mate;
	}

	private void handleShowBirthdayStatistics() {
		mainApp.showBirthdayStatistics();
	}

	/**
	 * Creates an empty address book.
	 */
	@FXML
	private void handleNew() {
		mainApp.model.getPersonData().clear();
		OpenFileCommandHandler.setPersonFilePath(null, mainApp.primaryStage);
	}

	/**
	 * Saves the file to the person file that is currently open. If there is no
	 * open file, the "save as" dialog is shown.
	 */
	@FXML
	private void handleSave() {
		File personFile = mainApp.getPersonFilePath();
		if (personFile != null) {
			mainApp.savePersonDataToFile(personFile);
		} else {
			handleSaveAs();
		}
	}

	/**
	 * Opens a FileChooser to let the user select a file to save to.
	 */
	@FXML
	private void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
			"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(mainApp.primaryStage);

		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			mainApp.savePersonDataToFile(file);
		}
	}

	/**
	 * Opens an about dialog.
	 */
	@FXML
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
	@FXML
	private void handleExit() {
		System.exit(0);
	}
}
