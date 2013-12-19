package org.svenehrke.javafxdemos.table.tablepair;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;
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

		TableViewState tableViewState = new TableViewState();

		final TableView<Person> leftTV = newTableView(items);
		leftTV.getColumns().addAll(col1(tableViewState), col2(tableViewState));

		final TableView<Person> rightTV = newTableView(items);
		rightTV.getColumns().addAll(col3(tableViewState), col4(tableViewState));

		VBox buttonBox = new VBox();
		buttonBox.setPadding(new Insets(10));
		buttonBox.setSpacing(10);

		Button shortTextButton = new Button("short text");
		shortTextButton.setOnAction((evt) -> items.get(1).name3Property().setValue(new TableCellItem("short", false)));

		Button longTextButton = new Button("long text");
		longTextButton.setOnAction((evt) -> items.get(1).name3Property().setValue(new TableCellItem("some longer text", false)));

		buttonBox.getChildren().addAll(shortTextButton, longTextButton);


		pane.getChildren().addAll(leftTV, rightTV, buttonBox);

		Scene scene = new Scene(pane, 650, 500);
		Styles.addStyleSheetTo(scene);
		stage.setScene(scene);
		stage.show();
	}

	private TableColumn<Person, TableCellItem> col1(TableViewState tableViewState) {
		final TableColumn<Person, TableCellItem> tc = new TableColumn<>("1");
		tc.setId("1");
		tc.setCellValueFactory(rowItem -> rowItem.getValue().name1Property());
		tc.setCellFactory(param -> new PersonTableCell(tableViewState));
		return tc;
	}

	private TableColumn<Person, TableCellItem> col2(TableViewState tableViewState) {
		final TableColumn<Person, TableCellItem> tc = new TableColumn<>("2");
		tc.setId("2");
		tc.setCellValueFactory(rowItem -> rowItem.getValue().name2Property());
		tc.setCellFactory(param -> new PersonTableCell(tableViewState));
		return tc;
	}
	private TableColumn<Person, TableCellItem> col3(TableViewState tableViewState) {
		final TableColumn<Person, TableCellItem> tc = new TableColumn<>("3");
		tc.setId("3");
		tc.setCellValueFactory(rowItem -> rowItem.getValue().name3Property());
		tc.setCellFactory(param -> new PersonTableCell(tableViewState));
		return tc;
	}
	private TableColumn<Person, TableCellItem> col4(TableViewState tableViewState) {
		final TableColumn<Person, TableCellItem> tc = new TableColumn<>("4");
		tc.setId("4");
		tc.setCellValueFactory(rowItem -> rowItem.getValue().name4Property());
		tc.setCellFactory(param -> new PersonTableCell(tableViewState));
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

}

