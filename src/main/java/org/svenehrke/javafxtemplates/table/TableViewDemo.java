package org.svenehrke.javafxtemplates.table;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.common.Styles;

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

		final TableView<Person> tableView = newTableView(items);
		tableView.getColumns().addAll(firstNameColumn(), lastNameColumn());

		pane.getChildren().addAll(tableView);

		Scene scene = new Scene(pane, 300, 500);
		Styles.addStyleSheetTo(scene);
		stage.setScene(scene);
		stage.show();
	}

	private TableColumn<Person, String> firstNameColumn() {
		final TableColumn<Person, String> firstNameColumn = new TableColumn<>("First Name");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		return firstNameColumn;
	}

	private TableColumn<Person, String> lastNameColumn() {
		final TableColumn<Person, String> lastNameColumn = new TableColumn<>("Last Name");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		return lastNameColumn;
	}

	private TableView<Person> newTableView(final ObservableList<Person> items) {
		final TableView<Person> tableView = new TableView<>(items);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		return tableView;
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

