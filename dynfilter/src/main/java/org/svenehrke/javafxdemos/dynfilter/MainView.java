package org.svenehrke.javafxdemos.dynfilter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements Initializable {

	@FXML
	TableView<Item> allItemsTV;

	@FXML
	TableColumn<Item, String> allNameColumn;

	@FXML
	TableColumn<Item, String> allColorColumn;

	@FXML
	TableView<Item> blueItemsTV;

	@FXML
	TableColumn<Item, String> blueNameColumn;

	@FXML
	TableColumn<Item, String> blueColorColumn;

	@FXML
	Button addRed;
	@FXML
	Button addBlue;
	@FXML
	Button makeRed;
	@FXML
	Button makeBlue;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// It seems to be impossible to define the selection mode with scenebuilder. That's why it is done here:
		allItemsTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
}
