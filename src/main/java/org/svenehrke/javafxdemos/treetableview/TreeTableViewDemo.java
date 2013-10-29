package org.svenehrke.javafxdemos.treetableview;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TreeTableViewDemo extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Tree View Sample");


		TreeTableView<MyFile> treeTable = new TreeTableView<MyFile>();
		treeTable.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);

		TreeItem<MyFile> root = new TreeItem<>(new MyFile("/", "1.1.2013"));
		root.getChildren().addAll(new TreeItem<>(new MyFile("a", "2.2.2013")));
		treeTable.setRoot(root);

		TreeTableColumn<MyFile, String> nameCol = new TreeTableColumn<>("First Name");
		nameCol.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getValue().nameProperty());
		// Using reflection approach:
		// nameCol.setCellValueFactory(new TreeItemPropertyValueFactory<MyFile, String>("name"));

		TreeTableColumn<MyFile, String> lastModifiedCol = new TreeTableColumn<>("Last Modified");
		lastModifiedCol.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getValue().lastModifiedProperty());

		treeTable.getColumns().addAll(nameCol, lastModifiedCol);


		StackPane pane = new StackPane();
		pane.getChildren().add(treeTable);
		primaryStage.setScene(new Scene(pane, 300, 250));
		primaryStage.show();
	}
}

