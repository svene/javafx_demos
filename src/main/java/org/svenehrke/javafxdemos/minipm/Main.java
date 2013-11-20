package org.svenehrke.javafxdemos.minipm;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.common.Styles;
import org.svenehrke.javafxdemos.table.editandvalidation.persistence.Person;
import org.svenehrke.javafxdemos.table.editandvalidation.persistence.PersonRepository;

public class Main extends Application {


	public static void main(String[] args) {
		launch(args);
	}

	AttributeStore attributeStore = new AttributeStore();
	MainAttributeScope mainAttributeScope = new MainAttributeScope(attributeStore);
	MainGUIScope mainGUIScope;

	@Override
	public void start(Stage stage) throws Exception {

		// 1: Create attributes and their internal binding:
		mainAttributeScope.initilizePMs();

		VBox pane = new VBox();
		pane.setPadding(new Insets(10));
		pane.setSpacing(10);

		// 2: Create widgets:
		Label label = new Label("-");
		TextField textField1 = new TextField();
		TextField textField2 = new TextField();
		Button button = new Button("Say Hello World");
		button.setOnAction(event -> System.out.println("hello world"));

		pane.getChildren().addAll(label, textField1, textField2, button);

		// 3: Bind Widgets/GUI to attributes:
		mainGUIScope = new MainGUIScope(mainAttributeScope, label, textField1, textField2);
		mainGUIScope.bind();

		// 4: Load data:
		load();

		Scene scene = new Scene(pane, 300, 500);
		Styles.addStyleSheetTo(scene);
		stage.setScene(scene);
		stage.setTitle("Mini PM");
		stage.show();
	}

	public void load() {
		Person person = PersonRepository.getInstance().people().stream().findFirst().get();
		mainAttributeScope.firstNameAttribute().value().setValue(person.getFirstName());
	}


}
