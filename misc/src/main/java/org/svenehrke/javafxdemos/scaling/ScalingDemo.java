package org.svenehrke.javafxdemos.scaling;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import org.tbee.javafx.scene.layout.MigPane;

public class ScalingDemo extends Application {


	public static final int WIDTH = 300;
	public static final int HEIGHT = 500;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		stage.setTitle("TableView Demo");

		final MigPane pane = new MigPane(
			"wrap 3, inset 10 10 10 10" // Layout Constraints
		);

		pane.setPadding(new Insets(10));

		ObservableList<Person> items = FXCollections.observableArrayList(people(100));
		final TableView<Person> tableView = new TableView<>(items);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		final TableColumn<Person, String> firstNameColumn = new TableColumn<>("First Name");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		final TableColumn<Person, String> lastNameColumn = new TableColumn<>("Last Name");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

		tableView.getColumns().addAll(firstNameColumn, lastNameColumn);

//		HBox hBox = new HBox();

		Button scale07 = new Button("0.7");
		Button scale15 = new Button("1.5");
		Button info = new Button("Info");
		pane.add(tableView, "span 3");
//		hBox.getChildren().addAll(scale07, scale15, info);

//		pane.getChildren().addAll(tableView, hBox);
		pane.add(scale07);
		pane.add(scale15);
		pane.add(info);

		Scene scene = new Scene(pane, WIDTH, HEIGHT);

		scale07.setOnAction(event -> scaleIt(pane, scene, stage, 0.7));
		scale15.setOnAction(event -> scaleIt(pane, scene, stage, 1.5));
		info.setOnAction(event -> info(stage, scene, pane));

		stage.setScene(scene);
		stage.show();
	}

	private void info(final Stage stage, final Scene scene, final MigPane pane) {
		System.out.println("pane.getWidth() = " + pane.getWidth());
		System.out.println("pane.getMinWidth() = " + pane.getMinWidth());
		System.out.println("scene.getWidth() = " + scene.getWidth());
		System.out.println("stage.getWidth() = " + stage.getWidth());
//		System.out.println("pane.getWidth() = " + pane.wigetWidth());
	}

	private void scaleIt(final Node pane, final Scene scene, final Stage stage, final double factor) {
		pane.getTransforms().clear();
		pane.getTransforms().add(new Scale(factor, factor));
		stage.setWidth(WIDTH * factor);
		stage.setHeight(HEIGHT * factor);
	}

	public static ObservableList<Person> people(int howMany) {
		ObservableList<Person> result = FXCollections.observableArrayList();
		for (int i = 0; i < howMany; i++) {
			result.add(new Person("firstname " + i, "lastname " + i));

		}
		return result;
	}

	public static class Person {
		private final String firstName;
		private final String lastName;

		public Person(final String firstName, final String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public String getFirstName() {
			return firstName;
		}

		public String getLastName() {
			return lastName;
		}
	}

}

