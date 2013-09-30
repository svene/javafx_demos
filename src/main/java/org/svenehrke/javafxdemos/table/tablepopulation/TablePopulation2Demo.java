package org.svenehrke.javafxdemos.table.tablepopulation;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collection;

/**
 * As 'TablePopulation1Demo' but using Integers calculated on demand from row index as values for first column.
 * This makes use of 'FakeCollections.integerItems(...)' and thus avoids the need for population of the table's items
 * which both saves memory and improves performance. As a side effect we receive the row index in the CellValueFactory since
 * the values the factories receive are created from the row index.
 *
 * Unfortunately, if we use 'FXCollections.observableArrayList(fakeCollection)' this still invokes 'get(idx)' 1_000_000 times
 * since it calls 'addAll(fakeCollection)' internally. We will solve this in the next demo.
 */
public class TablePopulation2Demo extends Application {


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		stage.setTitle("TableView Demo");

		VBox pane = new VBox();
		pane.setPadding(new Insets(10));

		Collection<Integer> items1 = FakeCollections.integerItems(1_000_000);
		ObservableList<Integer> items = FXCollections.observableArrayList(items1);
		final TableView<Integer> tableView = new TableView<>(items);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableView.getColumns().addAll(firstColumn(), secondColumn());

		pane.getChildren().addAll(tableView);

		Scene scene = new Scene(pane, 300, 500);
		stage.setScene(scene);
		stage.show();
	}

	private TableColumn<Integer, String> firstColumn() {
		TableColumn<Integer, String> result = new TableColumn<>("A");
		result.setCellValueFactory(param -> new SimpleObjectProperty<>(String.valueOf(param.getValue())));
		return result;
	}
	private TableColumn<Integer, String> secondColumn() {
		TableColumn<Integer, String> result = new TableColumn<>("B");
		result.setCellValueFactory(param -> new SimpleObjectProperty<>("b " + String.valueOf(param.getValue())));
		return result;
	}


}

