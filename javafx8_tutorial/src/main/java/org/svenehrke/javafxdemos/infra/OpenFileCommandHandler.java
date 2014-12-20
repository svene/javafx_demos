package org.svenehrke.javafxdemos.infra;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.address.Main;

import java.io.File;

/**
 * Opens a FileChooser to let the user select an address book to load.
 */
public class OpenFileCommandHandler implements Runnable {

	private final Mate mate;
	private final Main mainApp;
	private final Stage primaryStage;

	public OpenFileCommandHandler(Mate mate, Main mainApp, Stage primaryStage) {
		this.mate = mate;
		this.mainApp = mainApp;
		this.primaryStage = primaryStage;
	}

	@Override
	public void run() {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
			"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showOpenDialog(primaryStage);

		if (file != null) {
			mainApp.loadPersonDataFromFile(file);
		}
	}
}
