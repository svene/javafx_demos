package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

	private ObservableList<String2Bean> items;

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

		this.items = people();
		ObservableList<String2Bean> items = FXCollections.observableArrayList(this.items);
		final TableView<String2Bean> tableView = tableView(items);
		final TableColumn<String2Bean, String> firstNameColumn = firstNameColumn();
		final TableColumn<String2Bean, String> lastNameColumn = lastNameColumn();
		tableView.getColumns().addAll(firstNameColumn, lastNameColumn);

		Button button = new Button("show invalid entries");
		button.setOnAction(event -> showInvalidItems());

		pane.getChildren().addAll(tableView, button);

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

		ToUpperCaseStringConverter converter = converter();
		Function<String, ValidationResult> validator = firstNameValidator(converter);
		firstNameColumn.setCellFactory(it -> new ValidatingTextFieldTableCell<>(converter, validator));
		firstNameColumn.setEditable(true);
		firstNameColumn.setOnEditCommit(event -> {
			String2Bean item = event.getTableView().getItems().get(event.getTablePosition().getRow());
			item.setString1(event.getNewValue());

			ValidationResult validationResult = validator.apply(event.getNewValue());
			item.getString1Object().setValidationResult(validationResult);
		});

		firstNameColumn.setPrefWidth(100);

		return firstNameColumn;
	}

	private ToUpperCaseStringConverter converter() {
		return new ToUpperCaseStringConverter();
	}

	private TableColumn<String2Bean, String> lastNameColumn() {
		final TableColumn<String2Bean, String> lastNameColumn = new TableColumn<>("Last Name");
		lastNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getString2()));

		return lastNameColumn;
	}

	private Function<String, ValidationResult> firstNameValidator(final ToUpperCaseStringConverter converter) {
		return value -> {
			boolean valid = converter.toString(value).length() > 3;
			return new ValidationResult(valid, valid ? "" : "error: Length of first name must be greater than 3");
		};
	}

	private void showInvalidItems() {
		items
			.stream()
			.filter(item -> !item.getString1Object().isValid())
			.forEach(item -> System.out.printf("%s: %s %s%n", item.getString1Object().getValidationResult().getErrorMessage(), item.getString1(), item.getString2()))
		;
	}

	public ObservableList<String2Bean> people() {
		ObservableList<String2Bean> result = FXCollections.observableArrayList();
		peopleStream(firstNameValidator(converter())).forEach(result::add);
		return result;
	}

	Stream<String2Bean> peopleStream(Function<String, ValidationResult> validator) {
		List<String2Bean> result = Arrays.asList(
			newString2Bean(validatingFirstname("Essie", validator), "Vaill")
			, newString2Bean(validatingFirstname("Cruz", validator), "Roudabush")
			, newString2Bean(validatingFirstname("Billie", validator), "Tinnes")
			, newString2Bean(validatingFirstname("Zackary", validator), "Mockus")
			, newString2Bean(validatingFirstname("Rosemarie", validator), "Fifield")
			, newString2Bean(validatingFirstname("Bernard", validator), "Laboy")
			, newString2Bean(validatingFirstname("Sue", validator), "Haakinson")
			, newString2Bean(validatingFirstname("Valerie", validator), "Pou")
			, newString2Bean(validatingFirstname("Lashawn", validator), "Hasty")
			, newString2Bean(validatingFirstname("Marianne", validator), "Earman")
			, newString2Bean(validatingFirstname("Justina", validator), "Dragaj")
			, newString2Bean(validatingFirstname("Mandy", validator), "Mcdonnell")
			, newString2Bean(validatingFirstname("Conrad", validator), "Lanfear")
			, newString2Bean(validatingFirstname("Cyril", validator), "Behen")
			, newString2Bean(validatingFirstname("Shelley", validator), "Groden")
			, newString2Bean(validatingFirstname("Rosalind", validator), "Krenzke")
			, newString2Bean(validatingFirstname("Davis", validator), "Brevard")
		);
		return result.stream();
	}

	private ValidatingString validatingFirstname(final String firstname, final Function<String, ValidationResult> validator) {
		return new ValidatingString(firstname, validator);
	}

	private String2Bean newString2Bean(ValidatingString vs1, final String ln) {

		return new String2Bean(vs1, ln);
	}

}

