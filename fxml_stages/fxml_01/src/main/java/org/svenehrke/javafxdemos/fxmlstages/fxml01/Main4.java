package org.svenehrke.javafxdemos.fxmlstages.fxml01;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main4 extends Application {

	public static void main(String[] args) {
		Application.launch(Main4.class, args);
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
			Context4 context = new Context4();
			context.prefix = "Good morning";

			FXMLLoader loader = new FXMLLoader(Main4.class.getResource("/greet4.fxml"));
			Pane pane = loader.load();
			GreetView4 view = loader.getController();

			new ViewBinder4().bind(view, context);

			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Greet 4 FXML");
			primaryStage.show();
		} catch (Exception ex) {
			Logger.getLogger(Main4.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
