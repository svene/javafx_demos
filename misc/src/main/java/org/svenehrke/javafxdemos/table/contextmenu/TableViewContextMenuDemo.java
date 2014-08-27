package org.svenehrke.javafxdemos.table.contextmenu;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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


	public static final String FIRST_NAME_TITLE = "First Name";
	public static final String LAST_NAME_TITLE = "Last Name";

	public static void main(String[] args) {
		launch(args);
	}

	private StringProperty columnUnderMouse = new SimpleStringProperty("");
	private IntegerProperty rowUnderMouse = new SimpleIntegerProperty(-1);

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
		final TableColumn<Person, String> firstNameColumn = new TableColumn<>(FIRST_NAME_TITLE);
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		setCellFactory(firstNameColumn);
		return firstNameColumn;
	}

	private TableColumn<Person, String> lastNameColumn() {
		final TableColumn<Person, String> lastNameColumn = new TableColumn<>(LAST_NAME_TITLE);
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		setCellFactory(lastNameColumn);
		return lastNameColumn;
	}

	private void setCellFactory(final TableColumn<Person, String> column) {
		column.setCellFactory(param -> {
			TextFieldTableCell<Person, String> cell = new TextFieldTableCell<>(new DefaultStringConverter());
			cell.hoverProperty().addListener((s, o, n) -> {
				System.out.println("hover " + cell.getIndex() + "/" + cell.getTableColumn().getText());
				columnUnderMouse.set(cell.getTableColumn().getText());
				rowUnderMouse.set(cell.getIndex());
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

		columnUnderMouse.addListener((s,o,n) -> {
			ContextMenu contextMenu = columnUnderMouse.get().equals(FIRST_NAME_TITLE) ? firstNameContextMenu(rowUnderMouse.get()) : lastNameContextMenu(rowUnderMouse.get());
			tableView.setContextMenu(contextMenu);
		});
		rowUnderMouse.addListener((s,o,n) -> {
			ContextMenu contextMenu = columnUnderMouse.get().equals(FIRST_NAME_TITLE) ? firstNameContextMenu(rowUnderMouse.get()) : lastNameContextMenu(rowUnderMouse.get());
			tableView.setContextMenu(contextMenu);
		});

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

	private ContextMenu firstNameContextMenu(final int row) {
		ContextMenu menu = new ContextMenu();
		MenuItem item = new MenuItem("first name row " + row);
		item.setOnAction(event -> System.out.println("you've clicked the first name column on row: " + row));
		menu.getItems().add(item);
		return menu;
	}
	private ContextMenu lastNameContextMenu(final int row) {
		ContextMenu menu = new ContextMenu();
		MenuItem item = new MenuItem("last name row " + row);
		item.setOnAction(event -> System.out.println("you've clicked the last name column on row: " + row));
		menu.getItems().add(item);
		return menu;
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

