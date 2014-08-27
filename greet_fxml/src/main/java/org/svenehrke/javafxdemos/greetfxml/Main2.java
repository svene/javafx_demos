package org.svenehrke.javafxdemos.greetfxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
public class Main2 extends Application {

	public static void main(String[] args) {
		Application.launch(Main2.class, args);
	}

	@Override
	public void init() throws Exception {
	}

	@Override
	public void stop() throws Exception {
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		try {
			String s = "/greet.fxml";
			Pane pane = loadPane(s, p -> new GreetController2()); // <1> specify desired controller

			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Greet FXML");
			primaryStage.show();
		} catch (Exception ex) {
			Logger.getLogger(Main2.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private Pane loadPane(String s, Callback<Class<?>, Object> controllerFactory) {
		Pane pane;

		URL resource = Main2.class.getResource(s);
		ResourceBundle bundle = null;
		final FXMLLoader loader = new FXMLLoader(resource, bundle);
		loader.setControllerFactory(controllerFactory);
		try {
			pane = loader.load();
		} catch (IOException ex) {
			throw new IllegalStateException("Cannot load greet.fxml", ex);
		}
		return pane;
	}
}
