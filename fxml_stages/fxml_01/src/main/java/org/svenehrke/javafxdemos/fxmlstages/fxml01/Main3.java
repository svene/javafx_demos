package org.svenehrke.javafxdemos.fxmlstages.fxml01;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main3 extends Application {

	public static void main(String[] args) {
		Application.launch(Main3.class, args);
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
			FXMLLoader loader = new FXMLLoader(Main3.class.getResource("/greet3.fxml"));
			loader.setControllerFactory((Class<?> c) -> {
				GreetController3 controller = new GreetController3();
				controller.prefix = "Good morning"; // dependency injection simulation for demonstration purposes
				return controller;
			});
			Pane pane = loader.load();
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Greet 2 FXML");
			primaryStage.show();
		} catch (Exception ex) {
			Logger.getLogger(Main3.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
