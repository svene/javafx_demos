package org.svenehrke.javafxdemos.table.tablepopulation;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import javafx.util.converter.IntegerStringConverter;

import java.util.AbstractList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

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
public class LazyLoadingDemo extends Application {


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
		ObservableList<Person> items = FakeCollections.newObservableList(fakedPersonList);
		final TableView<Person> tableView = tableView(items);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableView.getColumns().addAll(firstColumn(), secondColumn());

		Label fillSizeLabel = new Label("0");
		fakedPersonList.fillSizeProperty().addListener((s,o,n) -> fillSizeLabel.setText(String.valueOf(n)));

		pane.getChildren().addAll(tableView, fillSizeLabel);

		Scene scene = new Scene(pane, 300, 500);
		stage.setScene(scene);
		stage.show();
	}

	private TableView<Person> tableView(final ObservableList<Person> items) {
		TableView<Person> tableView = new TableView<>(items);
		tableView.setRowFactory(param -> {
			TableRow<Person> row = new TableRow<>();
			row.indexProperty().addListener((observable, oldValue, visibleRowIndex) -> {
				int rowIdx = visibleRowIndex.intValue();
				if (rowIdx == -1) return;
				System.out.println("rowIdx = " + rowIdx);
				final Person person = tableView.getItems().get(rowIdx);
				if (person.getLoadState() == LoadState.LOADED) return;
				if (person.getLoadState() == LoadState.LOADING) return;

				// Simulate 'withPresentationModel' call:
				Executors.newSingleThreadExecutor().execute(() -> {
					// Simulate wait time until onFinishedHandler of 'dolphin.clientModelStore.withPresentationModel' is done:
					try {
						System.out.println("sending withPresentationModel for rowIdx " + rowIdx);
						person.setLoadState(LoadState.LOADING);
						Thread.sleep(300);
						System.out.println("got PM for rowIdx " + rowIdx);
					} catch (InterruptedException e) {
					}
					// Populate p2 from PM:
					person.setDbId(rowIdx); //pm.dbId
					person.firstNameProperty().setValue("first " + rowIdx); //pm.firstName
					person.lastNameProperty().setValue("last " + rowIdx); //pm.lastName
					person.setLoadState(LoadState.LOADED);
				});

			});
			return row;
		});
		return tableView;
	}

	private TableColumn<Person, String> firstColumn() {
		TableColumn<Person, String> result = new TableColumn<>("first");

		result.setCellValueFactory(param -> param.getValue().firstNameProperty());

		result.setCellFactory(TextFieldTableCell.forTableColumn());

		return result;
	}

	private TableColumn<Person, String> secondColumn() {
		TableColumn<Person, String> result = new TableColumn<>("last");
		result.setCellValueFactory(param -> param.getValue().lastNameProperty());
		return result;
	}

	public enum LoadState {
		NOT_LOADED, LOADING, LOADED
	}

	public static class Person {
		private final int rowIndex;
		private int dbId;
		private LoadState loadState;
		private final StringProperty firstName;
		private final StringProperty lastName;

		public Person(final int rowIndex, final int dbId, final String firstName, final String lastName) {
			this.rowIndex = rowIndex;
			this.dbId = dbId;
			this.firstName = new SimpleStringProperty(firstName);
			this.lastName = new SimpleStringProperty(lastName);
			loadState = LoadState.NOT_LOADED;
		}

		public int getRowIndex() {
			return rowIndex;
		}

		public int getDbId() {
			return dbId;
		}

		public void setDbId(final int dbId) {
			this.dbId = dbId;
		}

		public LoadState getLoadState() {
			return loadState;
		}

		public void setLoadState(final LoadState loadState) {
			this.loadState = loadState;
		}

		public String getFirstName() {
			return firstName.get();
		}

		public StringProperty firstNameProperty() {
			return firstName;
		}

		public String getLastName() {
			return lastName.get();
		}

		public StringProperty lastNameProperty() {
			return lastName;
		}
	}

	private static class FakedPersonList extends AbstractList<Person> {
		private final int size;

		private final IntegerProperty fillSize = new SimpleIntegerProperty(0);

		private final Map<Integer, Person> people = new HashMap<>();

		private FakedPersonList(final int size) {
			this.size = size;
		}

		@Override
		public Person get(final int index) {
			if (people.containsKey(index)) return people.get(index);

			System.out.println("creating person for rowIdx " + index + ". size: " + people.size());
			Person person = new Person(index, -1, "not loaded", "not loaded");
			people.put(index, person);
			fillSize.setValue(fillSize.getValue() + 1);
			return person;
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

