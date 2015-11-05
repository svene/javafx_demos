package org.svenehrke.javafxdemos.greetfxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

	private final Logger logger = Logger.getLogger(Main.class.getName());

	private static ApplicationSupporter applicationSupporter;

	public static void main(String[] args) {
		Main.applicationSupporter = new ApplicationSupporter("/greet.fxml", "bundles.app");
		Application.launch(Main.class, args);
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
			FXMLLoader loader = new FXMLLoader(applicationSupporter.fxmlUrl(), applicationSupporter.resources());
			loader.load();
			new GUIBinder(loader.getController(), new PresentationState()).bindAndInitialize();

			Scene scene = new Scene(loader.getRoot());
			scene.getStylesheets().add(getClass().getResource("/app.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle(loader.getResources().getString("title"));
			primaryStage.show();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
		}
	}

}
