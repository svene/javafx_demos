package org.svenehrke.javafxtemplates.simple;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloWorldApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		VBox pane = new VBox();
		pane.setPadding(new Insets(10));

		Button button = new Button("Say Hello World");
		button.setOnAction(event -> System.out.println("hello world"));

		pane.getChildren().addAll(button);

		Scene scene = new Scene(pane, 300, 500, Color.DODGERBLUE);
		stage.setScene(scene);
		stage.setTitle("Hello World");
		stage.show();
	}
}
