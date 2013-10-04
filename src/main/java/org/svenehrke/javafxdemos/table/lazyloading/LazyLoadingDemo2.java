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
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Lazy Loading Demo2 . As LazyLoadingDemo2 but lazy loading (loadPresentationModel call) is triggered by calls to 'FakedPersonList.get(int index)'
 * and thus the listener to 'TableRow.indexProperty' could be removed completely.
 *
 */
public class LazyLoadingDemo2 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		stage.setTitle("TableView Demo");

		VBox pane = new VBox();
		pane.setPadding(new Insets(10));
		pane.setSpacing(10);

		FakedPersonList fakedPersonList = new FakedPersonList(100_000, this::loadPresentationModel);
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
		return tableView;
	}

	private void loadPresentationModel(int rowIdx, final FXPerson person) {
		if (rowIdx == -1) return;
		System.out.println("loadPresentationModel: rowIdx = " + rowIdx);
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

	private static class FakedPersonList extends AbstractList<FXPerson> {
		private final int size;
		private final BiConsumer<Integer, FXPerson> getAtConsumer;

		private final IntegerProperty fillSize = new SimpleIntegerProperty(0);

		private final Map<Integer, FXPerson> people = new HashMap<>();

		private FakedPersonList(final int size, BiConsumer<Integer, FXPerson> getAtConsumer) {
			this.size = size;
			this.getAtConsumer = getAtConsumer;
		}

		@Override
		public FXPerson get(final int index) {
			if (people.containsKey(index)) {
				System.out.println("map hit for index = " + index);
				return people.get(index);
			}

			System.out.println("creating person for rowIdx " + index + ". size: " + people.size());
			FXPerson fxPerson = new FXPerson(index, -1, "not loaded", "not loaded");
			people.put(index, fxPerson);
			fillSize.setValue(fillSize.getValue() + 1);

			getAtConsumer.accept(index, fxPerson);

			return fxPerson;
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

