package org.svenehrke.javafxdemos.table.tablepopulation;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class TablePopulationRowFactoryDemo extends Application {


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		stage.setTitle("TableView Demo");

		VBox pane = new VBox();
		pane.setPadding(new Insets(10));

		ObservableList<Integer> items = FXCollections.observableArrayList(items(100));
		final TableView<Integer> tableView = new TableView<>(items);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableView.setRowFactory(param -> {
			TableRow<Integer> tableRow = new TableRow<>();
			tableRow.indexProperty().addListener((observable, oldValue, newValue) -> {
//						System.out.printf("index: %s -> %s%n", oldValue, newValue);
				System.out.println("withPresentationModel: requesting PM " + newValue);
				Executors.newSingleThreadExecutor().execute(() -> {
					// Simulate wait time until onFinishedHandler of 'dolphin.clientModelStore.withPresentationModel' is done:
					try {
						Thread.sleep(300);
						System.out.println("onFinishedHandler: got PM " + newValue);
					} catch (InterruptedException e) {
					}
				});
			});
			return tableRow;
		});

		final TableColumn<Integer, Integer> firstColumn = new TableColumn<>("A");
		firstColumn.setCellValueFactory(param -> {
			Integer value = param.getValue();

			return new SimpleObjectProperty<>(value);
		});
		tableView.getColumns().addAll(firstColumn);

		HBox hBox = new HBox();
		hBox.setSpacing(10);

		TextField tf = new TextField(String.valueOf(items.size()));

		Button button = new Button("populate with null entries");
		button.setOnAction(e -> tableView.setItems(items(Integer.valueOf(tf.getText()))));

		hBox.getChildren().addAll(tf, button);

		pane.getChildren().addAll(tableView, hBox);

		Scene scene = new Scene(pane, 300, 500);
		stage.setScene(scene);
		stage.show();
	}

	public static ObservableList<Integer> items(int howMany) {
		ObservableList<Integer> result = FXCollections.observableArrayList();
		for (int i = 0; i < howMany; i++) {
			result.add(new Integer(i));

		}
		System.out.println("done");
		return result;
	}



}

