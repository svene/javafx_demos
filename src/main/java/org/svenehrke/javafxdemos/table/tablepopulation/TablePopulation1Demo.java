package org.svenehrke.javafxdemos.table.tablepopulation;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

/**
 * Demo for TableView Population.
 * Demonstrates that it is not necessary that the items (the Collection passed into the 'new TableView(items)' constructor
 * really contains elements. It is sufficient that it is a Collections which pretends that it holds the elements. This is shown
 * by using 'FakeCollections.items(howMany)' where 'howMany' defines the number of rows the table should have.
 *
 * In the introduction example the row type of the table was 'Person'. In this example the row type was purposely chose to be 'Integer'
 * which normally would not make sense for a table (except for a one column table but then a list would probably make more sense).
 * We use integer here to demonstrate, that the type of the items does not really matter for the display purposes of the table.
 */
public class TablePopulation1Demo extends Application {


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		stage.setTitle("TableView Demo");

		VBox pane = new VBox();
		pane.setPadding(new Insets(10));

		Collection<Integer> items1 = FakeCollections.items(1_000_000);
		ObservableList<Integer> items = FXCollections.observableArrayList(items1);
		final TableView<Integer> tableView = new TableView<>(items);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableView.getColumns().addAll(firstColumn(), secondColumn());

		pane.getChildren().addAll(tableView);

		Scene scene = new Scene(pane, 300, 500);
		stage.setScene(scene);
		stage.show();
	}

	private TableColumn<Integer, String> firstColumn() { // Note: Integer as the row type (although not used) and String as the column type)
		TableColumn<Integer, String> result = new TableColumn<>("A");
		result.setCellValueFactory(param -> new SimpleObjectProperty<>("a")); // value 'a' simply made up here without relation to TableView.getItems()
		return result;
	}
	private TableColumn<Integer, String> secondColumn() {
		TableColumn<Integer, String> result = new TableColumn<>("B");
		result.setCellValueFactory(param -> new SimpleObjectProperty<>("b"));
		return result;
	}


}

