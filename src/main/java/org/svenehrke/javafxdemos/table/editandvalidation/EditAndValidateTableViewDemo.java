package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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

	private ObservableList<PersonTableBean> items = FXCollections.observableArrayList();
	private PersonTableBeanBuilder personTableBeanBuilder;
	private ListChangeListener<PersonTableBean> itemsListener;

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

		PersonTableSpecification tableSpecification = new PersonTableSpecification(Arrays.<IColumnSpecification>asList(
			new FirstNameColumnSpecification(),
			null,
			new BigDecimalColumnSpecification())
		);

		personTableBeanBuilder = PersonTableBeanBuilder.newPersonTableBeanBuilder(tableSpecification);

		itemsListener = (ListChangeListener<PersonTableBean>) c -> {
			while (c.next()) {
				System.out.println("CHANGE:" + c);
				if (c.wasRemoved()) {
					System.out.println("c.getRemovedSize() = " + c.getRemovedSize());
					PersonTableBean personTableBean = c.getRemoved().get(0);
					PersonRepository.getInstance().removePerson(personTableBean.getId());
				}
			}
		};
		loadItems();
		items.addAll(PersistenceAndGUIMapper.people(personTableBeanBuilder, personStream()));

		final TableView<PersonTableBean> tableView = tableView(items);

		final TableColumn<PersonTableBean, String> firstNameColumn = editableColumn(tableSpecification.getColumnSpecifications().get(0));
		final TableColumn<PersonTableBean, String> lastNameColumn = readOnlyColumn("Last Name", PersonTableBean::getLastName);
		final TableColumn<PersonTableBean, String> bigDecimalColumn = editableColumn(tableSpecification.getColumnSpecifications().get(2));

		tableView.getColumns().addAll(firstNameColumn, lastNameColumn, bigDecimalColumn);

		Button showInvalidItemsButton = new Button("show invalid entries");
		showInvalidItemsButton.setOnAction(event -> showInvalidItems());

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
		items.removeListener(itemsListener);
		items.clear();
		items.addListener(itemsListener);
	}
	private void loadItems() {
		items.removeListener(itemsListener);
		items.addAll(PersistenceAndGUIMapper.people(personTableBeanBuilder, personStream()));
		items.addListener(itemsListener);
	}

	public static Stream<Person> personStream() {
		return PersonRepository.getInstance().people().stream();
	}

	private void save() {
		//To change body of created methods use File | Settings | File Templates.
	}

	private void delItem(final TableView<PersonTableBean> tableView) {
		tableView.getSelectionModel().getSelectedIndices().forEach(idx -> items.remove((int)idx));
	}

	private void addItem() {
		long id = IdGenerator.next();
		items.add(personTableBeanBuilder.fromPresentation(id, "", "", ""));
		System.out.println("items.size() = " + items.size());
	}

	private TableView<PersonTableBean> tableView(final ObservableList<PersonTableBean> items) {
		final TableView<PersonTableBean> tableView = new TableView<>(items);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableView.setEditable(true);
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		return tableView;
	}

	private void showInvalidItems() {
		// todo: provide a combined validation result on 'PersonTableBean' instead of doing it here:
		System.out.println("Validation Results:");
		System.out.println("===================");
		items
			.stream()
			.filter(item -> !item.firstName().isValid())
			.forEach(item -> System.out.printf("Validation Error: %s %s: %s%n", item.firstName().getText(), item.getLastName(), item.firstName().getValidationResult().getErrorMessage()))
		;
		items
			.stream()
			.filter(item -> !item.bigDecimalValue().isValid())
			.forEach(item -> System.out.printf("Validation Error: %s %s: %s%n", item.firstName().getText(), item.getLastName(), item.bigDecimalValue().getValidationResult().getErrorMessage()))
		;
	}

}

