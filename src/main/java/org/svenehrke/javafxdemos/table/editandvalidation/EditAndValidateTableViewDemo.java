package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import org.svenehrke.javafxdemos.common.Styles;
import org.svenehrke.javafxdemos.table.editandvalidation.String2Bean;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
		final TableView<String2Bean> tableView = new TableView<>(items);
		setupTableView(tableView);

		final TableColumn<String2Bean, String> firstNameColumn = new TableColumn<>("First Name");
		setupFirstNameColumn(tableView, firstNameColumn);


		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("string1"));
		final TableColumn<String2Bean, String> lastNameColumn = new TableColumn<>("Last Name");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("string2"));

		tableView.getColumns().addAll(firstNameColumn, lastNameColumn);

		pane.getChildren().addAll(tableView);

		Scene scene = new Scene(pane, 300, 500, Color.DODGERBLUE);
		Styles.addStyleSheetTo(scene);
		stage.setScene(scene);
		stage.show();
	}

	private void setupTableView(final TableView<String2Bean> tableView) {
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableView.setEditable(true);
	}

	private void setupFirstNameColumn(final TableView<String2Bean> tableView, final TableColumn<String2Bean, String> firstNameColumn) {
		firstNameColumn.setCellFactory(it -> new ValidatingTextFieldTableCell<>(new DefaultStringConverter()));
		firstNameColumn.setEditable(true);
		firstNameColumn.setOnEditCommit(event -> {
			System.out.println("name.commit: event.getNewValue() = " + event.getNewValue());
			System.out.println(event.getSource().getClass().getName());
			event.getTableView().getItems().get(event.getTablePosition().getRow()).setString1(event.getNewValue());
		});

		firstNameColumn.setPrefWidth(100);
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

