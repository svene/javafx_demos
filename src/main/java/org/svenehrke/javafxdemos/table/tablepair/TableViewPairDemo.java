package org.svenehrke.javafxdemos.table.tablepair;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.common.Styles;

public class TableViewPairDemo extends Application {


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		stage.setTitle(getClass().getSimpleName());

		HBox pane = new HBox();
		pane.setPadding(new Insets(10));
		pane.setSpacing(10);

		ObservableList<Person> items = FXCollections.observableArrayList(people(100));

		final TableView<Person> leftTV = newTableView(items);
		leftTV.getColumns().addAll(col1(), col2());

		final TableView<Person> rightTV = newTableView(items);
		rightTV.getColumns().addAll(col3(), col4());

		VBox buttonBox = new VBox();
		buttonBox.setPadding(new Insets(10));
		buttonBox.setSpacing(10);

		Button shortTextButton = new Button("short text");
		shortTextButton.getStyleClass().addAll("small");
		shortTextButton.setOnAction((evt) -> items.get(1).name3Property().setValue("short"));

		Button longTextButton = new Button("long text");
		longTextButton.setOnAction((evt) -> items.get(1).name3Property().setValue("some longer text"));

		buttonBox.getChildren().addAll(shortTextButton, longTextButton);


		pane.getChildren().addAll(leftTV, rightTV, buttonBox);

		Scene scene = new Scene(pane, 650, 500);
		Styles.addStyleSheetTo(scene);
		stage.setScene(scene);
		stage.show();
	}

	private TableColumn<Person, String> col1() {
		final TableColumn<Person, String> tc = new TableColumn<>("1");
		tc.setCellValueFactory(rowItem -> rowItem.getValue().name1Property());
		tc.setCellFactory(param -> new PersonTableCell());
		return tc;
	}

	private TableColumn<Person, String> col2() {
		final TableColumn<Person, String> tc = new TableColumn<>("2");
		tc.setCellValueFactory(rowItem -> rowItem.getValue().name2Property());
		tc.setCellFactory(param -> new PersonTableCell());
		return tc;
	}
	private TableColumn<Person, String> col3() {
		final TableColumn<Person, String> tc = new TableColumn<>("3");
		tc.setCellValueFactory(rowItem -> rowItem.getValue().name3Property());
		tc.setCellFactory(param -> new PersonTableCell());
		return tc;
	}
	private TableColumn<Person, String> col4() {
		final TableColumn<Person, String> tc = new TableColumn<>("4");
		tc.setCellValueFactory(rowItem -> rowItem.getValue().name4Property());
		tc.setCellFactory(param -> new PersonTableCell());
		return tc;
	}

	private TableView<Person> newTableView(final ObservableList<Person> items) {
		final TableView<Person> tableView = new TableView<>(items);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		return tableView;
	}

	public static ObservableList<Person> people(int howMany) {
		ObservableList<Person> result = FXCollections.observableArrayList();
		for (int i = 0; i < howMany; i++) {
			result.add(new Person(i));

		}
		return result;
	}

	public static class Person {
		private final StringProperty name1;
		private final StringProperty name2;
		private final StringProperty name3;
		private final StringProperty name4;

		public Person(final int rowIdx) {
			this.name1 = newProperty(1, rowIdx);
			this.name2 = newProperty(2, rowIdx);
			this.name3 = newProperty(3, rowIdx);
			this.name4 = newProperty(4, rowIdx);
		}

		private SimpleStringProperty newProperty(int colIdx, final int rowIdx) {
			return new SimpleStringProperty("name " + colIdx + " / " + rowIdx);
		}

		public StringProperty name1Property() {
			return name1;
		}

		public StringProperty name2Property() {
			return name2;
		}

		public StringProperty name3Property() {
			return name3;
		}

		public StringProperty name4Property() {
			return name4;
		}
	}

}

