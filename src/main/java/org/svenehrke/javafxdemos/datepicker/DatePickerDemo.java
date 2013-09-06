package org.svenehrke.javafxdemos.datepicker;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 * from http://learnjavafx.typepad.com/weblog/2013/08/quick-and-dirty-javafx-8-datepicker-example.html
 */
public class DatePickerDemo extends Application {

	private LocalDate localDate;

	@Override
	public void start(Stage stage) throws Exception {
		VBox pane = new VBox();
		pane.setPadding(new Insets(10));

		DatePicker datePicker = new DatePicker();
		datePicker.setOnAction(event -> {
			localDate = datePicker.getValue();
			System.out.println(localDate);
		});

		pane.getChildren().addAll(datePicker);

		Scene scene = new Scene(pane, 300, 500, Color.DODGERBLUE);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
