package org.svenehrke.javafxdemos.table.customtablecell;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.common.Styles;

public class CustomTableCellTableViewDemo extends Application {


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		stage.setTitle(getClass().getSimpleName());

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
		final TableColumn<Person, String> tc = new TableColumn<>("First Name");
		tc.setCellValueFactory(rowItem -> rowItem.getValue().name1Property());
		tc.setCellFactory(param -> new PersonTableCell());
		return tc;
	}

	private TableColumn<Person, String> lastNameColumn() {
		final TableColumn<Person, String> tc = new TableColumn<>("Last Name");
		tc.setCellValueFactory(rowItem -> rowItem.getValue().name2Property());
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

}

