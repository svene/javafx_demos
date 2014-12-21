package org.svenehrke.javafxdemos.infra;

import javafx.stage.Stage;
import org.svenehrke.javafxdemos.address.Model;

public class NewAddressbookCommandHandler implements Runnable {

	private final Stage primaryStage;
	private final Model model;

	public NewAddressbookCommandHandler(Stage primaryStage, Model model) {
		this.primaryStage = primaryStage;
		this.model = model;
	}

	@Override
	public void run() {
		model.getPersonData().clear();
		OpenFileCommandHandler.setPersonFilePath(null, primaryStage);
	}
}
