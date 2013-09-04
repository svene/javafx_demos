package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.common.Styles;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class EditAndValidateTableViewDemo extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
	}

	@Override
	public void stop() throws Exception {
	}

	@Override
	public void start(Stage stage) throws Exception {
		VBox pane = new VBox();
		pane.setPadding(new Insets(10));

		ObservableList<String2Bean> items = FXCollections.observableArrayList(people());
		final TableView<String2Bean> tableView = tableView(items);
		final TableColumn<String2Bean, String> firstNameColumn = firstNameColumn();
		final TableColumn<String2Bean, String> lastNameColumn = lastNameColumn();
		tableView.getColumns().addAll(firstNameColumn, lastNameColumn);

		pane.getChildren().addAll(tableView);

		Scene scene = new Scene(pane, 300, 500, Color.DODGERBLUE);
		Styles.addStyleSheetTo(scene);
		stage.setScene(scene);
		stage.show();
	}

	private TableView<String2Bean> tableView(final ObservableList<String2Bean> items) {
		final TableView<String2Bean> tableView = new TableView<>(items);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableView.setEditable(true);
		return tableView;
	}

	private TableColumn<String2Bean, String> firstNameColumn() {
		final TableColumn<String2Bean, String> firstNameColumn = new TableColumn<>("First Name");

		firstNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getString1()));

		ToUpperCaseStringConverter converter = new ToUpperCaseStringConverter();
		Function<String, ValidationResult> validator = value -> new ValidationResult(converter.toString(value).length() > 3);
		firstNameColumn.setCellFactory(it -> new ValidatingTextFieldTableCell<>(converter, validator));
		firstNameColumn.setEditable(true);
		firstNameColumn.setOnEditCommit(event -> {
			String2Bean item = event.getTableView().getItems().get(event.getTablePosition().getRow());
			item.setString1(event.getNewValue());

			ValidationResult validationResult = validator.apply(event.getNewValue());
			System.out.println("validationResult = " + validationResult.isValid);
		});

		firstNameColumn.setPrefWidth(100);

		return firstNameColumn;
	}

	private TableColumn<String2Bean, String> lastNameColumn() {
		final TableColumn<String2Bean, String> lastNameColumn = new TableColumn<>("Last Name");
		lastNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getString2()));

		return lastNameColumn;
	}

	public ObservableList<String2Bean> people() {
		ObservableList<String2Bean> result = FXCollections.observableArrayList();
		peopleStream().forEach(result::add);
		return result;
	}

	Stream<String2Bean> peopleStream() {
		List<String2Bean> result = Arrays.asList(
			new String2Bean("Essie","Vaill")
			,new String2Bean("Cruz","Roudabush")
			,new String2Bean("Billie","Tinnes")
			,new String2Bean("Zackary","Mockus")
			,new String2Bean("Rosemarie","Fifield")
			,new String2Bean("Bernard","Laboy")
			,new String2Bean("Sue","Haakinson")
			,new String2Bean("Valerie","Pou")
			,new String2Bean("Lashawn","Hasty")
			,new String2Bean("Marianne","Earman")
			,new String2Bean("Justina","Dragaj")
			,new String2Bean("Mandy","Mcdonnell")
			,new String2Bean("Conrad","Lanfear")
			,new String2Bean("Cyril","Behen")
			,new String2Bean("Shelley","Groden")
			,new String2Bean("Rosalind","Krenzke")
			,new String2Bean("Davis","Brevard")
		);
		return result.stream();
	}

}

