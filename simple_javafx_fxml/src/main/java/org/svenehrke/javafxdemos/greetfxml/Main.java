package org.svenehrke.javafxdemos.greetfxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

	private final Logger logger = Logger.getLogger(Main.class.getName());

	private static ApplicationConfig applicationConfig;

	public static void main(String[] args) {
		Main.applicationConfig = new ApplicationConfig();
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
			FXMLLoader loader = new FXMLLoader(applicationConfig.fxmlUrlSupplier().get(), applicationConfig.resources().get());
			loader.load();
			Parent parent = loader.getRoot();

			applicationConfig.binder().accept(loader);
			new GUIBinder(loader.getController(), new PresentationState()).bindAndInitialize();

			Scene scene = new Scene(parent);
			scene.getStylesheets().add(getClass().getResource("/app.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle(loader.getResources().getString("title"));
			primaryStage.show();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
		}
	}

}
