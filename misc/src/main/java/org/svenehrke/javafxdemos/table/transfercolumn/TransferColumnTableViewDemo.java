package org.svenehrke.javafxdemos.table.transfercolumn;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TransferColumnTableViewDemo extends Application {


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		stage.setTitle("Transfer Column Demo");

		HBox hBox = new HBox();
		hBox.setSpacing(10);
		VBox pane = new VBox();
		pane.setPadding(new Insets(10));
		pane.setSpacing(10);

		ObservableList<Person> items = FXCollections.observableArrayList(people(100));
		final TableView<Person> tv1 = newTableView(items);

		final TableColumn<Person, String> firstNameColumn = firstNameColumn();
		final TableColumn<Person, String> lastNameColumn = lastNameColumn();

		tv1.getColumns().addAll(firstNameColumn, lastNameColumn);

		final TableView<Person> tv2 = newTableView(items);
		tv2.getColumns().addAll(lastNameColumn());
		hBox.getChildren().addAll(tv1, tv2);

		Button move = new Button("Transfer Column");
		move.setOnAction(event -> {
			TableColumn<Person, ?> personTableColumn = tv1.getColumns().get(0);
			tv1.getColumns().remove(0);
			tv2.getColumns().addAll(personTableColumn);
		});
		pane.getChildren().addAll(hBox, move);

		Scene scene = new Scene(pane, 400, 500, Color.DODGERBLUE);
		stage.setScene(scene);
		stage.show();
	}

	private TableColumn<Person, String> lastNameColumn() {
		final TableColumn<Person, String> lastNameColumn = new TableColumn<>("Last Name");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		return lastNameColumn;
	}

	private TableColumn<Person, String> firstNameColumn() {
		final TableColumn<Person, String> firstNameColumn = new TableColumn<>("First Name");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		return firstNameColumn;
	}

	private TableView<Person> newTableView(final ObservableList<Person> items) {
		TableView<Person> result = new TableView<>(items);
		result.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		return result;
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

