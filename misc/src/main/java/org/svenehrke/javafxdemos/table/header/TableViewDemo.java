package org.svenehrke.javafxdemos.table.header;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class TableViewDemo extends Application {


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		stage.setTitle("TableView Demo");

		VBox pane = new VBox();
		pane.setPadding(new Insets(10));

		ObservableList<Person> items = FXCollections.observableArrayList(people(100));
		final TableView<Person> tableView = new TableView<>(items);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		final TableColumn<Person, String> firstNameColumn = new TableColumn<>("First Name");
		TextField firstNameSearch = new TextField("bla");
		firstNameSearch.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
			if (KeyCode.ENTER == event.getCode()) {
				System.out.println("ja");
			}
		});
		firstNameColumn.setGraphic(firstNameSearch);
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		final TableColumn<Person, String> lastNameColumn = new TableColumn<>("Last Name");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

		tableView.getColumns().addAll(firstNameColumn, lastNameColumn);

		pane.getChildren().addAll(tableView);

		Scene scene = new Scene(pane, 300, 500);
		stage.setScene(scene);
		stage.show();
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

