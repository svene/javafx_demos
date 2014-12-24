package org.svenehrke.javafxdemos.address;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 *
 * @author Marco Jakob
 */
public class RootLayoutView {

	@FXML
	MenuItem miNew;
	@FXML
	MenuItem miOpen;
	@FXML
	MenuItem miSave;
	@FXML
	MenuItem miSaveAs;
	@FXML
	MenuItem miShowStatistics;
}
