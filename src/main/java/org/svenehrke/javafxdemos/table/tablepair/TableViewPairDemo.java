package org.svenehrke.javafxdemos.table.tablepair;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.common.Nodes;
import org.svenehrke.javafxdemos.common.Styles;

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
		leftTV.getColumns().addAll(col1(tableViewState), col2(tableViewState));

		final TableView<Person> rightTV = newTableView(items);
		rightTV.setId(Constants.RIGHT_TV_ID);
		rightTV.getColumns().addAll(col3(tableViewState)/*, col4(tableViewState)*/);

		bindTables(leftTV, rightTV);

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
		});
	}

	private TableColumn<Person, String> col1(TableViewState tableViewState) {
		final TableColumn<Person, String> tc = new TableColumn<>(Constants.COL_1_ID);
		tc.setId(Constants.COL_1_ID);
		tc.setCellValueFactory(rowItem -> rowItem.getValue().name1Property());
		tc.setCellFactory(param -> new PersonTableCell(tableViewState));
		return tc;
	}

	private TableColumn<Person, String> col2(TableViewState tableViewState) {
		final TableColumn<Person, String> tc = new TableColumn<>(Constants.COL_2_ID);
		tc.setId(Constants.COL_2_ID);
		tc.setCellValueFactory(rowItem -> rowItem.getValue().name2Property());
		tc.setCellFactory(param -> new PersonTableCell(tableViewState));
		return tc;
	}
	private TableColumn<Person, String> col3(TableViewState tableViewState) {
		final TableColumn<Person, String> tc = new TableColumn<>(Constants.COL_3_ID);
		tc.setId(Constants.COL_3_ID);
		tc.setCellValueFactory(rowItem -> rowItem.getValue().name3Property());
		tc.setCellFactory(param -> new ButtonTableCell(tableViewState));
		return tc;
	}
	private TableColumn<Person, String> col4(TableViewState tableViewState) {
		final TableColumn<Person, String> tc = new TableColumn<>(Constants.COL_4_ID);
		tc.setId(Constants.COL_4_ID);
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

