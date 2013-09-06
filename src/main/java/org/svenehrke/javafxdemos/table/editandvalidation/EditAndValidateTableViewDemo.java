package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;
import org.svenehrke.javafxdemos.common.Styles;

import static org.svenehrke.javafxdemos.table.editandvalidation.ColumnConstructor.editableColumn;
import static org.svenehrke.javafxdemos.table.editandvalidation.ColumnConstructor.readOnlyColumn;

public class EditAndValidateTableViewDemo extends Application {

	private ObservableList<PersonTableBean> items;

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
		pane.setSpacing(10);

		this.items = DataProvider.people(DataProvider.newPersonTableBeanBuilder());
		ObservableList<PersonTableBean> items = FXCollections.observableArrayList(this.items);
		final TableView<PersonTableBean> tableView = tableView(items);
		final TableColumn<PersonTableBean, String> firstNameColumn = editableColumn("First Name", PersonTableBean::firstName, new ToUpperCaseStringConverter());
		final TableColumn<PersonTableBean, String> lastNameColumn = readOnlyColumn("Last Name", PersonTableBean::getLastName);
		final TableColumn<PersonTableBean, String> bigDecimalColumn = editableColumn("BigDecimal", PersonTableBean::bigDecimalValue, new DefaultStringConverter());
		tableView.getColumns().addAll(firstNameColumn, lastNameColumn, bigDecimalColumn);

		Button button = new Button("show invalid entries");
		button.setOnAction(event -> showInvalidItems());

		pane.getChildren().addAll(tableView, button);

		Scene scene = new Scene(pane, 400, 500);
		Styles.addStyleSheetTo(scene);
		stage.setScene(scene);
		stage.show();
	}

	private TableView<PersonTableBean> tableView(final ObservableList<PersonTableBean> items) {
		final TableView<PersonTableBean> tableView = new TableView<>(items);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableView.setEditable(true);
		return tableView;
	}

	private void showInvalidItems() {
		// todo: provide a combined validation result on 'PersonTableBean' instead of doing it here:
		items
			.stream()
			.filter(item -> !item.firstName().isValid())
			.forEach(item -> System.out.printf("%s: %s %s%n", item.firstName().getValidationResult().getErrorMessage(), item.firstName().getText(), item.getLastName()))
		;
		items
			.stream()
			.filter(item -> !item.bigDecimalValue().isValid())
			.forEach(item -> System.out.printf("%s: %s %s%n", item.bigDecimalValue().getValidationResult().getErrorMessage(), item.firstName().getText(), item.getLastName()))
		;
	}

}

