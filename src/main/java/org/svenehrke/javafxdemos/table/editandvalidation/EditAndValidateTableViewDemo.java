package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.common.Styles;
import org.svenehrke.javafxdemos.table.editandvalidation.persistence.IdGenerator;
import org.svenehrke.javafxdemos.table.editandvalidation.persistence.Person;
import org.svenehrke.javafxdemos.table.editandvalidation.persistence.PersonRepository;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.svenehrke.javafxdemos.table.editandvalidation.ColumnBuilder.editableColumn;
import static org.svenehrke.javafxdemos.table.editandvalidation.ColumnBuilder.readOnlyColumn;

public class EditAndValidateTableViewDemo extends Application {

	private final ObservableList<PersonTableBean> itemsToBeDeleted = FXCollections.observableArrayList();
	private ObservableList<PersonTableBean> items = FXCollections.observableArrayList();

	private PersonTableBeanBuilder personTableBeanBuilder;

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

		ObservableList<IColumnSpecification> columnSpecifications = FXCollections.observableArrayList();
		columnSpecifications.addAll(new FirstNameColumnSpecification(), null, new BigDecimalColumnSpecification());

		personTableBeanBuilder = PersonTableBeanBuilder.newPersonTableBeanBuilder(columnSpecifications);

		loadItems();
		final TableView<PersonTableBean> tableView = tableView(items);

		final TableColumn<PersonTableBean, String> firstNameColumn = editableColumn(PersonTableSpecification2.firstNameSpec(columnSpecifications));
		final TableColumn<PersonTableBean, String> lastNameColumn = readOnlyColumn("Last Name", PersonTableBean::getLastName);
		final TableColumn<PersonTableBean, String> bigDecimalColumn = editableColumn(PersonTableSpecification2.bdValueSpec(columnSpecifications));

		tableView.getColumns().addAll(firstNameColumn, lastNameColumn, bigDecimalColumn);

		Button showInvalidItemsButton = new Button("show entries");
		showInvalidItemsButton.setOnAction(event -> showInvalidItems(tableView));

		Button addButton = new Button("+");
		addButton.setOnAction(event -> addItem());

		Button delButton = new Button("-");
		delButton.setOnAction(event -> delItem(tableView));

		Button saveButton = new Button("Save");
		saveButton.setOnAction(event -> save());

		Button clearButton = new Button("Clear");
		clearButton.setOnAction(event -> clear());

		Button loadButton = new Button("Load");
		loadButton.setOnAction(event -> loadItems());

		HBox hBox = new HBox(10);
		hBox.getChildren().addAll(showInvalidItemsButton, addButton, delButton, saveButton, clearButton, loadButton);


		pane.getChildren().addAll(tableView, hBox);

		Scene scene = new Scene(pane, 400, 500);
		Styles.addStyleSheetTo(scene);
		stage.setScene(scene);
		stage.show();
	}

	private void clear() {
		items.clear();
		itemsToBeDeleted.clear();
	}
	private void loadItems() {
		itemsToBeDeleted.clear();
		ObservableList<PersonTableBean> people = PersistenceAndGUIMapper.people(personTableBeanBuilder, personStream());
		items.addAll(people);
	}

	public static Stream<Person> personStream() {
		return PersonRepository.getInstance().people().stream();
	}

	private void delItem(final TableView<PersonTableBean> tableView) {
		tableView.getSelectionModel().getSelectedItems().forEach(itemsToBeDeleted::add);
		itemsToBeDeleted.forEach(items::remove);
	}

	private void addItem() {
		long id = IdGenerator.next();
		items.add(personTableBeanBuilder.fromPresentation(id, "", "", ""));
		System.out.println("items.size() = " + items.size());
	}

	private void showInvalidItems(final TableView<PersonTableBean> tableView) {
		// todo: provide a combined validation result on 'PersonTableBean' instead of doing it here:
		System.out.println("Status of Items:");
		System.out.println("================");
		for (PersonTableBean item : items) {
			checkValidity(item, item.firstName());
			checkValidity(item, item.bigDecimalValue());
		}
		itemsToBeDeleted.forEach(item -> System.out.printf("Delete: %s %s%n", item.firstName().getText(), item.getLastName()));
		tableView.getSelectionModel().getSelectedItems().forEach(item -> {
			System.out.printf("selected: %d, %s%n", item.getId(), item.toString());
		});
	}

	private void save() {
		for (PersonTableBean item : itemsToBeDeleted) {
			System.out.printf("deleting: %d, %s%n", item.getId(), item.toString());
			PersonRepository.getInstance().removePerson(item.getId());
		}
		itemsToBeDeleted.clear();
	}

	private void checkValidity(final PersonTableBean item, ValidatedString validatedString) {
		if (!validatedString.isValid()) {
			System.out.printf("Validation Error: %s %s: %s%n", item.firstName().getText(), item.getLastName(), validatedString.getValidationResult().getErrorMessage());
		}
	}

	private TableView<PersonTableBean> tableView(final ObservableList<PersonTableBean> items) {
		final TableView<PersonTableBean> tableView = new TableView<>(items);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableView.setEditable(true);
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		return tableView;
	}

}

