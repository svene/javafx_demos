package org.svenehrke.javafxdemos.table.tablepopulation;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * As 'TablePopulation2Demo' but using 'FakeCollections.integerObservableList()' instead of ' FXCollections.observableArrayList'.
 * Internally that makes use of a 'ObservableListWrapper' which wraps the original list instead of copying it's elements. Since
 * there is no copy process involved the iteration for 'addAll' does not happen anymore which leads to lazy calls to 'get(idx)'.
 */
public class TablePopulation3Demo extends Application {


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		stage.setTitle("TableView Demo");

		VBox pane = new VBox();
		pane.setPadding(new Insets(10));

		ObservableList<Integer> items = FakeCollections.integerObservableList(FakeCollections.integerItems(1_000_000));  // <== instead of ' FXCollections.observableArrayList'
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

