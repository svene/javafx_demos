package org.svenehrke.javafxdemos.table.lazyloading;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.table.tablepopulation.FakeCollections;

import java.util.AbstractList;
import java.util.HashMap;
import java.util.Map;

/**
 * Lazy Loading Demo.
 *
 * Uses 'FakedPersonList' in order to lazily instantiate Beans (FXPerson) for TableView's items.
 * The faked PersonList is initialized with a certain number of entries (e.g. 100'000). Note the individual entries will not
 * be created in advance which would consume a lot of memory even though it is clear that not all entries will be used of even shown.
 * Instead the will be created on demand when FakedPersonList.get(index) is called by the TableView.
 *
 * When created, FXPerson objects are empty except for the field 'rowIndex' which is populated
 * with the index of the corresponding entry in the table's item list.
 *
 *
 * In this approach the population of the FXPerson objects is triggered by a listener of TableRow.indexProperty.
 * TableRow.indexProperty is fired when a row of the TableView will become visible. The listener then retrieves the
 * real data from the backend (asynchronously) and when loaded populates the FXPerson objects with the data from the backend
 * (in this demo the "backend" sends a "presentation model" against which the FXPerson object will be bound).
 *
 * Additional notes:
 *
 * - 'FakedPersonList.get(int index)' is always called (even several times for the same index) before the notifier of 'TableRow.indexProperty' fires.
 *   This makes it a much better candidate to trigger the population with the real data and will be shown in the next demo.
 *
 */
public class LazyLoadingDemo1 extends Application {


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		stage.setTitle("TableView Demo");

		VBox pane = new VBox();
		pane.setPadding(new Insets(10));
		pane.setSpacing(10);

		FakedPersonList fakedPersonList = new FakedPersonList(100_000);
		ObservableList<FXPerson> items = FakeCollections.newObservableList(fakedPersonList);
		final TableView<FXPerson> tableView = tableView(items);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableView.getColumns().addAll(firstColumn(), secondColumn());

		Label fillSizeLabel = new Label("0");
		fakedPersonList.fillSizeProperty().addListener((s,o,n) -> fillSizeLabel.setText(String.valueOf(n)));

		pane.getChildren().addAll(tableView, fillSizeLabel);

		Scene scene = new Scene(pane, 300, 500);
		stage.setScene(scene);
		stage.show();
	}

	private TableView<FXPerson> tableView(final ObservableList<FXPerson> items) {
		TableView<FXPerson> tableView = new TableView<>(items);
		tableView.setRowFactory(param -> {
			TableRow<FXPerson> row = new TableRow<>();
			row.indexProperty().addListener((observable, oldValue, visibleRowIndex) -> {
				int rowIdx = visibleRowIndex.intValue();
				if (rowIdx == -1) return;
				System.out.println("TableRow.indexProperty: rowIdx = " + rowIdx);
				final FXPerson person = tableView.getItems().get(rowIdx);
				if (person.getLoadState() == LoadState.LOADED) return;
				if (person.getLoadState() == LoadState.LOADING) return;

				// Simulate 'withPresentationModel' call:
				person.setLoadState(LoadState.LOADING);
				PMProvider.withPresentationModel(rowIdx, (pm) -> {
					// Populate p2 from PM:
					person.setDbId(rowIdx); //pm.dbId
					person.firstNameProperty().bindBidirectional(pm.firstNameProperty());
					person.lastNameProperty().bindBidirectional(pm.lastNameProperty());
					person.setLoadState(LoadState.LOADED);
				});

			});
			return row;
		});
		return tableView;
	}

	private TableColumn<FXPerson, String> firstColumn() {
		TableColumn<FXPerson, String> result = new TableColumn<>("first");

		result.setCellValueFactory(param -> param.getValue().firstNameProperty());

		result.setCellFactory(TextFieldTableCell.forTableColumn());

		return result;
	}

	private TableColumn<FXPerson, String> secondColumn() {
		TableColumn<FXPerson, String> result = new TableColumn<>("last");
		result.setCellValueFactory(param -> param.getValue().lastNameProperty());
		return result;
	}

	public enum LoadState {
		NOT_LOADED, LOADING, LOADED
	}

	private static class FakedPersonList extends AbstractList<FXPerson> {
		private final int size;

		private final IntegerProperty fillSize = new SimpleIntegerProperty(0);

		private final Map<Integer, FXPerson> people = new HashMap<>();

		private FakedPersonList(final int size) {
			this.size = size;
		}

		@Override
		public FXPerson get(final int index) {
			if (people.containsKey(index)) {
				System.out.println("map hit for index = " + index);
				return people.get(index);
			}

			System.out.println("creating person for rowIdx " + index + ". size: " + people.size());
			FXPerson FXPerson = new FXPerson(index, -1, "not loaded", "not loaded");
			people.put(index, FXPerson);
			fillSize.setValue(fillSize.getValue() + 1);
			return FXPerson;
		}

		@Override
		public int size() {
			return size;
		}

		private IntegerProperty fillSizeProperty() {
			return fillSize;
		}
	}
}

