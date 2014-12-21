package org.svenehrke.javafxdemos.infra;

import javafx.stage.Stage;
import org.svenehrke.javafxdemos.address.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Mate {

	private Map<String, Runnable> commands = new HashMap<>();

	private Model model;
	Stage primaryStage;


	public void addCommand(String commandId, Runnable command) {
		commands.put(commandId, command);
	}

	public Optional<Runnable> getCommand(String commandId) {
		return Optional.ofNullable(commands.get(commandId));
	}

	public void handleCommand(String commandId) {
		Optional<Runnable> optional = getCommand(commandId);
		if (optional.isPresent()) {
			optional.get().run();
		}
	}

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
