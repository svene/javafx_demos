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

		firstNameColumn.setCellValueFactory(param -> param.getValue().string1Object().textProperty());

		firstNameColumn.setCellFactory(it -> new CustomizableUpdateItemTextFieldTableCell<>(new ToUpperCaseStringConverter(), (cell, item) -> {
			int idx = cell.getIndex();
			if (idx < 0 || idx > items.size() - 1) return; // sometimes strange indexes come in which we will simply ignore

			String2Bean string2Bean = items.get(idx);
			boolean isValid = string2Bean.string1Object().isValid();
			if (item != null) {
				cell.pseudoClassStateChanged(Styles.CSS_PC_INVALID, !isValid);
			}
			else {
				cell.pseudoClassStateChanged(Styles.CSS_PC_INVALID, isValid);
			}
		}));

		firstNameColumn.setEditable(true);
		firstNameColumn.setOnEditCommit(event -> {
			String2Bean item = event.getTableView().getItems().get(event.getTablePosition().getRow());
			item.setString1(event.getNewValue());
		});

		firstNameColumn.setPrefWidth(100);

		return firstNameColumn;
	}

	private TableColumn<String2Bean, String> lastNameColumn() {
		final TableColumn<String2Bean, String> lastNameColumn = new TableColumn<>("Last Name");
		lastNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getString2()));

		return lastNameColumn;
	}

	private Function<String, ValidationResult> firstNameValidator() {
		return value -> {
			boolean valid = value.length() > 3;
			return new ValidationResult(valid, valid ? "" : "error: Length of first name must be greater than 3");
		};
	}

	private void showInvalidItems() {
		items
			.stream()
			.filter(item -> !item.string1Object().isValid())
			.forEach(item -> System.out.printf("%s: %s %s%n", item.string1Object().getValidationResult().getErrorMessage(), item.getString1(), item.getString2()))
		;
	}

	public ObservableList<String2Bean> people() {
		ObservableList<String2Bean> result = FXCollections.observableArrayList();
		peopleStream(firstNameValidator()).forEach(result::add);
		return result;
	}

	Stream<String2Bean> peopleStream(Function<String, ValidationResult> validator) {
		List<String2Bean> result = Arrays.asList(
			newString2Bean("Essie", "Vaill", validator)
			, newString2Bean("Cruz", "Roudabush", validator)
			, newString2Bean("Billie", "Tinnes", validator)
			, newString2Bean("Zackary", "Mockus", validator)
			, newString2Bean("Rosemarie", "Fifield", validator)
			, newString2Bean("Bernard", "Laboy", validator)
			, newString2Bean("Sue", "Haakinson", validator)
			, newString2Bean("Valerie", "Pou", validator)
			, newString2Bean("Lashawn", "Hasty", validator)
			, newString2Bean("Marianne", "Earman", validator)
			, newString2Bean("Justina", "Dragaj", validator)
			, newString2Bean("Mandy", "Mcdonnell", validator)
			, newString2Bean("Conrad", "Lanfear", validator)
			, newString2Bean("Cyril", "Behen", validator)
			, newString2Bean("Shelley", "Groden", validator)
			, newString2Bean("Rosalind", "Krenzke", validator)
			, newString2Bean("Davis", "Brevard", validator)
		);
		return result.stream();
	}

	private String2Bean newString2Bean(String firstName, final String lastName, Function<String, ValidationResult> firstnameValidator) {
		String2Bean result = new String2Bean("", "");
		ValidatedStrings.bindValidatorToValidatedString(result.string1Object(), firstnameValidator);
		result.setString1(firstName);
		result.setString2(lastName);
		return result;
	}

}

