package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.util.StringConverter;
import org.svenehrke.javafxdemos.common.Styles;

import java.util.function.Function;

class ColumnConstructor {

	static TableColumn<PersonTableBean, String> editableColumn(String columnTitle, Function<PersonTableBean, ValidatedString> validatedStringProvider, StringConverter<String> converter) {
		final TableColumn<PersonTableBean, String> result = new TableColumn<>(columnTitle);

		result.setCellValueFactory(param -> validatedStringProvider.apply(param.getValue()).textProperty());

		result.setCellFactory(it -> new CustomizableUpdateItemTextFieldTableCell<>(converter, (cell, value) -> {
			int idx = cell.getIndex();
			// Do not handle situations with strange indexes which sometimes occur:
			if (idx < 0 || idx > cell.getTableView().getItems().size() - 1) return;

			boolean isValid = validatedStringProvider.apply(cell.getTableView().getItems().get(idx)).isValid();
			if (value != null) {
				cell.pseudoClassStateChanged(Styles.CSS_PC_INVALID, !isValid);
			} else {
				cell.pseudoClassStateChanged(Styles.CSS_PC_INVALID, isValid);
			}
		}));

		result.setEditable(true);
		result.setOnEditCommit(event -> {
			PersonTableBean item = event.getTableView().getItems().get(event.getTablePosition().getRow());
			validatedStringProvider.apply(item).setText(event.getNewValue());
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
