package org.svenehrke.javafx.demo.d006communicatingbetweenwindows;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.util.function.Consumer;

public class ConfirmBox {

	public static void display(String title, String message, Consumer<Boolean> consumer) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		Label label = new Label();
		label.setText(message);

		//Create two buttons
		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");

		//Clicking will set answer and close window
		yesButton.setOnAction(e -> {
			consumer.accept(Boolean.TRUE);
			window.close();
		});
		noButton.setOnAction(e -> {
			consumer.accept(Boolean.FALSE);
			window.close();
		});

		VBox layout = new VBox(10);

		//Add buttons
		layout.getChildren().addAll(label, yesButton, noButton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}

}

