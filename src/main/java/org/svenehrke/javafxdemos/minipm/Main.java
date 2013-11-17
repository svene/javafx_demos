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
import org.svenehrke.javafxdemos.common.Styles;
import org.svenehrke.javafxdemos.table.editandvalidation.persistence.Person;
import org.svenehrke.javafxdemos.table.editandvalidation.persistence.PersonRepository;

import java.awt.event.KeyEvent;
import java.util.*;

public class Main extends Application {


	public static void main(String[] args) {
		launch(args);
	}

	AttributeStore attributeStore = new AttributeStore();
	MainScope mainScope = new MainScope(attributeStore);

	@Override
	public void start(Stage stage) throws Exception {

		// 1: Create attributes and their internal binding:
		mainScope.initializePresentationModels();

		VBox pane = new VBox();
		pane.setPadding(new Insets(10));
		pane.setSpacing(10);

		Label label = new Label("-");

		// 2: Bind Widgets/GUI to attributes:
		mainScope.getFirstNameAttribute().value().bindBidirectional(label.textProperty());

		// First TextField: change attribute with each key pressed
		TextField textField1 = new TextField("-");
		mainScope.getFirstNameAttribute().value().bindBidirectional(textField1.textProperty());
		mainScope.getFirstNameValidAttribute().value().addListener((s,o,n) -> textField1.pseudoClassStateChanged(Styles.CSS_PC_INVALID, !(Boolean) n));

		// Second TextField: only change attribute, when ENTER is pressed
		TextField textField2 = new TextField("-");
		textField2.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
			if (KeyCode.ENTER == event.getCode()) {
				mainScope.getFirstNameAttribute().value().setValue(textField2.getText());
			}
		});
		mainScope.getFirstNameAttribute().value().addListener((s,o,n) -> textField2.setText((String) n));

		// 3: Load data:
		mainScope.load();

		Button button = new Button("Say Hello World");
		button.setOnAction(event -> System.out.println("hello world"));

		pane.getChildren().addAll(label, textField1, textField2, button);

		Scene scene = new Scene(pane, 300, 500);
		Styles.addStyleSheetTo(scene);
		stage.setScene(scene);
		stage.setTitle("Mini PM");
		stage.show();
	}



}
