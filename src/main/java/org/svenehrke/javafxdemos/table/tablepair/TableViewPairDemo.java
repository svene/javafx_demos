package org.svenehrke.javafxdemos.table.tablepair;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.common.Nodes;
import org.svenehrke.javafxdemos.common.Styles;
import org.svenehrke.javafxdemos.common.TableViews;

public class TableViewPairDemo extends Application {


	private BooleanProperty bothTablesAvailableProperty;

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


		leftTV.getColumns().addAll(name1Column(tableViewState), name2Column(tableViewState), heightHelper(name3Column(tableViewState)));

		final TableView<Person> rightTV = newTableView(items);
		rightTV.setId(Constants.RIGHT_TV_ID);
		rightTV.getColumns().addAll(col21(tableViewState)/*, col22(tableViewState)*/);

		bindTables(leftTV, rightTV);

		alignRowHeights(leftTV, rightTV);

		VBox buttonBox = new VBox();
		buttonBox.setPadding(new Insets(10));
		buttonBox.setSpacing(10);

		Button shortTextButton = new Button("short text");
		shortTextButton.setOnAction((evt) -> items.get(1).name3Property().setValue("short"));

		Button longTextButton = new Button("long text");
		longTextButton.setOnAction((evt) -> items.get(1).name3Property().setValue("some longer textsome longer text"));

		buttonBox.getChildren().addAll(shortTextButton, longTextButton);


		pane.getChildren().addAll(leftTV, rightTV, buttonBox);

		Scene scene = new Scene(pane, 650, 500);
		Styles.addStyleSheetTo(scene);
		stage.setScene(scene);
		stage.show();
	}

	private void bindTables(final TableView<Person> leftTV, final TableView<Person> rightTV) {
		bothTablesAvailableProperty = new SimpleBooleanProperty();
		bothTablesAvailableProperty.bind(Bindings.isNotNull(leftTV.skinProperty()).and(Bindings.isNotNull(rightTV.skinProperty())));
		bothTablesAvailableProperty.addListener((s,o,n) -> {
			if (!n) return;
			ScrollBar leftVScrollBar = Nodes.verticalScrollBarFrom(leftTV);
			ScrollBar rightVScrollBar = Nodes.verticalScrollBarFrom(rightTV);

			leftVScrollBar.valueProperty().bindBidirectional(rightVScrollBar.valueProperty());

			Platform.runLater(() -> {
				TableViews.getTableViewInfo(rightTV).getVirtualFlow().getFirstVisibleCellWithinViewPort().indexProperty().addListener((s2, o2, n2) -> {
					System.out.printf("+++ Right Table: first visible row: %s -> %s%n", o2, n2);
				});
			});


		});
	}

	private void alignRowHeights(final TableView<Person> leftTV, final TableView<Person> rightTV) {
		bothTablesAvailableProperty.addListener((s,o,n) -> {
			if (!n) return;
/*
			Platform.runLater(() -> {
				TableViews.showTVInfo(leftTV, "LEFT");
				TableViews.showTVInfo(rightTV, "RIGHT");
			});
*/
		});
	}

	private TableColumn<Person, String> heightHelper(TableColumn<Person, String> tc) {
		tc.setMinWidth(0.0);
		tc.setMaxWidth(0.0);
		tc.impl_setWidth(0.0);
		return tc;
	}

	private TableColumn<Person, String> name1Column(TableViewState tableViewState) {
		final TableColumn<Person, String> tc = new TableColumn<>(Constants.COL_11_ID);
		tc.setId(Constants.COL_11_ID);
		tc.setCellValueFactory(rowItem -> rowItem.getValue().name1Property());
		tc.setCellFactory(param -> new PersonTableCell(tableViewState));
		return tc;
	}
	private TableColumn<Person, String> name2Column(TableViewState tableViewState) {
		final TableColumn<Person, String> tc = new TableColumn<>(Constants.COL_12_ID);
		tc.setId(Constants.COL_12_ID);
		tc.setCellValueFactory(rowItem -> rowItem.getValue().name2Property());
		tc.setCellFactory(param -> new PersonTableCell(tableViewState));
		return tc;
	}

	private TableColumn<Person, String> name3Column(TableViewState tableViewState) {
		final TableColumn<Person, String> tc = new TableColumn<>(Constants.COL_13_ID);
		tc.setId(Constants.COL_13_ID);
		tc.setCellValueFactory(rowItem -> rowItem.getValue().name3Property());
		tc.setCellFactory(param -> new ButtonTableCell(tableViewState, true));
		return tc;
	}
	private TableColumn<Person, String> col21(TableViewState tableViewState) {
		final TableColumn<Person, String> tc = new TableColumn<>(Constants.COL_21_ID);
		tc.setId(Constants.COL_21_ID);
		tc.setCellValueFactory(rowItem -> rowItem.getValue().name3Property());
		tc.setCellFactory(param -> new ButtonTableCell(tableViewState, false));
		return tc;
	}
	private TableColumn<Person, String> col22(TableViewState tableViewState) {
		final TableColumn<Person, String> tc = new TableColumn<>(Constants.COL_22_ID);
		tc.setId(Constants.COL_22_ID);
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

