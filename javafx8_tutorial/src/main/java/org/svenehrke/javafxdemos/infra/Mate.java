package org.svenehrke.javafxdemos.infra;

import javafx.stage.Stage;
import org.svenehrke.javafxdemos.address.Model;

public class Mate {

	private Model model;
	Stage primaryStage;


	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
}
