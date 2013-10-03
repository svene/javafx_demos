package org.svenehrke.javafxdemos.table.tablepopulation;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * As 'TablePopulation4Demo' but with editing support.
 * This means:
 * 1) calling 'setEditable(true)' on the tableView and the TableColumns
 * 2) setting an appropriate CellFactory
 * 3) calling 'setOnEditCommit()' on the TableColumns
 *
 * This demo implements all 3) and when you execute the demo it is possible to change a value in the first column
 * say in the second row from '1' to '111'. But when you double click the cell again to edit it a second time you
 * will notice that it shows again '1' in the cell editor since 3) is not really implementing a commit. This will
 * be done in the next demo.
 *
 */
public class TablePopulation5Demo extends Application {


	public static void main(String[] args) {
		launch(args);
	}

	Map<Integer, String> data = new HashMap();

	@Override
	public void start(final Stage stage) throws Exception {
		stage.setTitle("TableView Demo");

		VBox pane = new VBox();
		pane.setPadding(new Insets(10));

		ObservableList<Integer> items = FakeCollections.newObservableList(FakeCollections.integerItems(1_000_000));
		final TableView<Integer> tableView = tableView(items);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableView.getColumns().addAll(firstColumn(), secondColumn());

		pane.getChildren().addAll(tableView);

		Scene scene = new Scene(pane, 300, 500);
		stage.setScene(scene);
		stage.show();
	}

	private TableView<Integer> tableView(final ObservableList<Integer> items) {
		TableView<Integer> result = new TableView<>(items);
		result.setEditable(true);
		return result;
	}

	private TableColumn<Integer, String> firstColumn() {
		TableColumn<Integer, String> result = new TableColumn<>("A");

		result.setCellValueFactory(param -> new SimpleObjectProperty<>(String.valueOf(data.containsKey(param.getValue()) ? data.get(param.getValue()) :  param.getValue())));

		// make it editable:
		result.setEditable(true);
		result.setCellFactory(TextFieldTableCell.forTableColumn());
		result.setOnEditCommit(event -> {
			System.out.printf("COMMIT: rowvalue: %s, oldvalue: %s, newvalue: %s%n", event.getRowValue(), event.getOldValue(), event.getNewValue());
			data.put(event.getRowValue(), event.getNewValue());
		});

		return result;
	}
	private TableColumn<Integer, String> secondColumn() {
		TableColumn<Integer, String> result = new TableColumn<>("B");
		result.setEditable(true);
		result.setCellValueFactory(param -> new SimpleObjectProperty<>("b " + String.valueOf(param.getValue())));
		return result;
	}


}

