package org.svenehrke.javafxdemos.infra;

import javafx.stage.Stage;
import org.svenehrke.javafxdemos.address.Model;

public class SaveAsCommandHandler implements Runnable {

	private final Stage primaryStage;
	private final Model model;

	public SaveAsCommandHandler(Stage primaryStage, Model model) {
		this.primaryStage = primaryStage;
		this.model = model;
	}

	@Override
	public void run() {
		SaveCommandHandler.handleSaveAs(primaryStage, model);
	}

}
