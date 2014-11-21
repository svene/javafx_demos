package org.svenehrke.javafxdemos.fxmlstages.fxml01;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main1 extends Application {

	public static void main(String[] args) {
		Application.launch(Main1.class, args);
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
			Pane pane = FXMLLoader.load(Main1.class.getResource("/greet1.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Greet 1 FXML");
			primaryStage.show();
		} catch (Exception ex) {
			Logger.getLogger(Main1.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
