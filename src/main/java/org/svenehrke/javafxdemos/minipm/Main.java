package org.svenehrke.javafxdemos.minipm;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.table.editandvalidation.persistence.Person;
import org.svenehrke.javafxdemos.table.editandvalidation.persistence.PersonRepository;

import java.awt.event.KeyEvent;
import java.util.*;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	AttributeStore attributeStore = new AttributeStore();

	@Override
	public void start(Stage stage) throws Exception {

		load();

		VBox pane = new VBox();
		pane.setPadding(new Insets(10));
		pane.setSpacing(10);

		Label label = new Label("-");
		Attribute attribute = attributeStore.get(1L);
		attribute.value().bindBidirectional(label.textProperty());

		TextField textField1 = new TextField("-");
		attribute.value().bindBidirectional(textField1.textProperty());

		TextField textField2 = new TextField("-");
		textField2.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
			if (KeyCode.ENTER == event.getCode()) {
				attribute.value().setValue(textField2.getText());
			}
		});
		attribute.value().addListener((s,o,n) -> textField2.setText(n));

		Button button = new Button("Say Hello World");
		button.setOnAction(event -> System.out.println("hello world"));

		pane.getChildren().addAll(label, textField1, textField2, button);

		Scene scene = new Scene(pane, 300, 500);
		stage.setScene(scene);
		stage.setTitle("Mini PM");
		stage.show();
	}

	private void load() {
		Optional<Person> first = PersonRepository.getInstance().people().stream().findFirst();
		Person person = first.get();

		Attribute attribute = new Attribute(person.getFirstName());
		attributeStore.put(1L, attribute);
	}

}
