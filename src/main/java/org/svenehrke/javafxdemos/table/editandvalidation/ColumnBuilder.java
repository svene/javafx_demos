package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import org.svenehrke.javafxdemos.common.Styles;

import java.util.function.Function;

class ColumnBuilder {

	static TableColumn<PersonTableBean, String> editableColumn(final IColumnSpecification columnSpecification) {
		final TableColumn<PersonTableBean, String> result = new TableColumn<>(columnSpecification.title());

		result.setCellValueFactory(param -> columnSpecification.validatedStringProvider().apply(param.getValue()).textProperty());

		result.setCellFactory(it -> new CustomizableUpdateItemTextFieldTableCell<>(columnSpecification.convenienceConverter(), (cell, value) -> {
			int idx = cell.getIndex();
			// Do not handle situations with strange indexes which sometimes occur:
			if (idx < 0 || idx > cell.getTableView().getItems().size() - 1) return;

			boolean isValid = columnSpecification.validatedStringProvider().apply(cell.getTableView().getItems().get(idx)).isValid();
			if (value != null) {
				cell.pseudoClassStateChanged(Styles.CSS_PC_INVALID, !isValid);
			} else {
				cell.pseudoClassStateChanged(Styles.CSS_PC_INVALID, isValid);
			}
		}));

		result.setEditable(true);
		result.setOnEditCommit(event -> {
			PersonTableBean item = event.getTableView().getItems().get(event.getTablePosition().getRow());
			columnSpecification.validatedStringProvider().apply(item).textProperty().setValue(event.getNewValue());
		});

		result.setPrefWidth(100);

		return result;
	}

	static TableColumn<PersonTableBean, String> readOnlyColumn(String columnTitle, Function<PersonTableBean, String> stringProvider) {
		final TableColumn<PersonTableBean, String> lastNameColumn = new TableColumn<>(columnTitle);
		lastNameColumn.setCellValueFactory(param -> new SimpleStringProperty(stringProvider.apply(param.getValue())));

		return lastNameColumn;
	}
}
