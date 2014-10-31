package org.svenehrke.javafxdemos.address;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Like 'Main' but the pane is loaded explicitly which allows to configure the controller (GreetController2 in this example) in the code
 * and not in the fxml.
 */
public class Main extends Application {

	public static void main(String[] args) {
		Application.launch(Main.class, args);
	}

	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void init() throws Exception {
	}

	@Override
	public void stop() throws Exception {
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");

		initRootLayout();
		showPersonOverview();

	}

	private void initRootLayout() {
		rootLayout = loadPane("/RootLayout.fxml", p -> new GreetController()); // <1> specify desired controller

		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void showPersonOverview() {
		AnchorPane personOverview = loadPane("/PersonOverview.fxml", p -> new GreetController()); // <1> specify desired controller
		rootLayout.setCenter(personOverview);
	}

	private static <T> T loadPane(String s, Callback<Class<?>, Object> controllerFactory) {

		URL resource = Main.class.getResource(s);
		ResourceBundle bundle = null;
		final FXMLLoader loader = new FXMLLoader(resource, bundle);
		loader.setControllerFactory(controllerFactory);
		T pane;
		try {
			pane = loader.load();
		} catch (IOException ex) {
			throw new IllegalStateException("Cannot load greet.fxml", ex);
		}
		return pane;
	}
}
