package org.svenehrke.javafxdemos.address;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import org.controlsfx.dialog.Dialogs;
import org.svenehrke.javafxdemos.infra.Mate;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 *
 * @author Marco Jakob
 */
public class RootLayoutController {

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
		miNew.setOnAction(event -> mate.handleCommand(Api.NEW_ADDRESS_BOOK));
		miOpen.setOnAction(event -> mate.handleCommand(Api.OPEN_FILE));
		miSave.setOnAction(event -> mate.handleCommand(Api.SAVE));
		miSaveAs.setOnAction(event -> mate.handleCommand(Api.SAVE_AS));
		miShowStatistics.setOnAction(event -> mate.handleCommand(Api.STATISTICS));
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mate
	 */
	public void setMainApp(Mate mate) {
		this.mate = mate;
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
