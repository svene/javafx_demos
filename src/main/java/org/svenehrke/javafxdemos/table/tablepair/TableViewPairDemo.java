package org.svenehrke.javafxdemos.table.tablepair;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.common.Nodes;
import org.svenehrke.javafxdemos.common.Styles;

public class TableViewPairDemo extends Application {


	private BooleanProperty bothTablesAvailableProperty;
	private StringProperty rowProperty = new SimpleStringProperty();


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

		TableViewState tableViewState = new TableViewState(items);

		final TableView<Person> leftTV = newTableView(items);
		leftTV.setId(Constants.LEFT_TV_ID);


		leftTV.getColumns().addAll(
			name1Column(Constants.COL_L1_ID, tableViewState),
			name2Column(Constants.COL_L2_ID, tableViewState),
			heightHelperColumn(name3Column(Constants.COL_L3_ID, tableViewState))
		);

		final TableView<Person> rightTV = newTableView(items);
		rightTV.setId(Constants.RIGHT_TV_ID);
		rightTV.getColumns().addAll(
			name3Column(Constants.COL_R1_ID, tableViewState),
			name4Column(Constants.COL_R2_ID, tableViewState)
		);

		bindTables(leftTV, rightTV);

		VBox buttonBox = new VBox();
		buttonBox.setPadding(new Insets(10));
		buttonBox.setSpacing(10);

		Node rowIndexWidget = rowIndexWidget(rowProperty);
		rowProperty.set("1");

		Button shortTextButton = new Button("short text");
		shortTextButton.setOnAction((evt) -> items.get(Integer.valueOf(rowProperty.get())).name3Property().setValue(Person.LONG_TEXT_ARRAY[0]));

		Button longTextButton = new Button("long text");
		longTextButton.setOnAction((evt) -> items.get(Integer.valueOf(rowProperty.get())).name3Property().setValue(Person.LONG_TEXT_ARRAY[2]));

		buttonBox.getChildren().addAll(rowIndexWidget, shortTextButton, longTextButton);


		pane.getChildren().addAll(leftTV, rightTV, buttonBox);

		Scene scene = new Scene(pane, 650, 500);
		Styles.addStyleSheetTo(scene);
		stage.setScene(scene);
		stage.show();
	}

	private Node rowIndexWidget(StringProperty rowProperty) {
		VBox vBox = new VBox();
		TextField tf = new TextField("");
		tf.textProperty().bindBidirectional(rowProperty);
		vBox.getChildren().addAll(new Label("row:"), tf);
		return vBox;
	}

	private void bindTables(final TableView<Person> leftTV, final TableView<Person> rightTV) {
		bothTablesAvailableProperty = new SimpleBooleanProperty();
		bothTablesAvailableProperty.bind(Bindings.isNotNull(leftTV.skinProperty()).and(Bindings.isNotNull(rightTV.skinProperty())));
		bothTablesAvailableProperty.addListener((s,o,n) -> {
			if (!n) return;
			ScrollBar leftVScrollBar = Nodes.verticalScrollBarFrom(leftTV);
			ScrollBar rightVScrollBar = Nodes.verticalScrollBarFrom(rightTV);

			leftVScrollBar.valueProperty().bindBidirectional(rightVScrollBar.valueProperty());
		});
	}

	private TableColumn<Person, String> heightHelperColumn(TableColumn<Person, String> tc) {
		tc.setMinWidth(0.0);
		tc.setMaxWidth(0.0);
		return tc;
	}

	private TableColumn<Person, String> name1Column(String id, TableViewState tableViewState) {
		final TableColumn<Person, String> tc = new TableColumn<>(id);
		tc.setId(id);
		tc.setCellValueFactory(rowItem -> rowItem.getValue().name1Property());
		tc.setCellFactory(param -> new PersonTableCell(tableViewState));
		return tc;
	}
	private TableColumn<Person, String> name2Column(String id, TableViewState tableViewState) {
		final TableColumn<Person, String> tc = new TableColumn<>(id);
		tc.setId(id);
		tc.setCellValueFactory(rowItem -> rowItem.getValue().name2Property());
		tc.setCellFactory(param -> new PersonTableCell(tableViewState));
		return tc;
	}

	private TableColumn<Person, String> name3Column(String id, TableViewState tableViewState) {
		final TableColumn<Person, String> tc = new TableColumn<>(id);
		tc.setId(id);
		tc.setCellValueFactory(rowItem -> rowItem.getValue().name3Property());
		tc.setCellFactory(param -> new ButtonTableCell(tableViewState));
		return tc;
	}

	private TableColumn<Person, String> name4Column(String id, TableViewState tableViewState) {
		final TableColumn<Person, String> tc = new TableColumn<>(id);
		tc.setId(id);
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

