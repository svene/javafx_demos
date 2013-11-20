package org.svenehrke.javafxdemos.table.contextmenu;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;

public class TableViewContextMenuDemo extends Application {


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
		stage.setScene(scene);
		stage.show();
	}

	private TableColumn<Person, String> firstNameColumn() {
		final TableColumn<Person, String> firstNameColumn = new TableColumn<>("First Name");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		setCellFactory(firstNameColumn);
		return firstNameColumn;
	}

	private TableColumn<Person, String> lastNameColumn() {
		final TableColumn<Person, String> lastNameColumn = new TableColumn<>("Last Name");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		setCellFactory(lastNameColumn);
		return lastNameColumn;
	}

	private void setCellFactory(final TableColumn<Person, String> column) {
		column.setCellFactory(param -> {
			TextFieldTableCell<Person, String> cell = new TextFieldTableCell<>(new DefaultStringConverter());
			cell.hoverProperty().addListener((s, o, n) -> {
				System.out.println("hover " + cell.getIndex() + "/" + cell.getTableColumn().getText());
			});
			return cell;
		});
		boolean useMyCell = false;
		if (useMyCell) {
			column.setCellFactory(param -> new MyCell());
		}
	}

	private TableView<Person> newTableView(final ObservableList<Person> items) {
		final TableView<Person> tableView = new TableView<>(items);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		addContextMenuTo(tableView);
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

	private void addContextMenuTo(final TableView<Person> tableView) {
		ContextMenu menu = new ContextMenu();
		MenuItem item = new MenuItem("you can click me");
		item.setOnAction(event -> System.out.println("you've clicked the menu item"));
		menu.getItems().add(item);
		tableView.setContextMenu(menu);
	}

	static class MyCell<S> extends TableCell<S, String> {

		MyCell() {
			hoverProperty().addListener((s,o,n) -> {
				System.out.println("hover: " + getIndex() + "/" + getTableColumn().getText());
			});
		}

		@Override
		protected void updateItem(final String item, final boolean empty) {
			super.updateItem(item, empty);    //To change body of overridden methods use File | Settings | File Templates.
			setText(item);
		}
	}

}

