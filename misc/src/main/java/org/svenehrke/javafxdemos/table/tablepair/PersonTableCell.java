package org.svenehrke.javafxdemos.table.tablepair;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

class PersonTableCell extends TableCell<Person, String> {

	private final TextField textField = new TextField();

	PersonTableCell(TableViewState tableViewState) {
		indexProperty().addListener((s,o,n) -> {
			int idx = n.intValue();
			if (idx < 0 || idx >= getTableView().getItems().size()) return;
			tableViewState.put(idx, getTableColumn().getId(), this);
		});
	}

	@Override
	protected void updateItem(final String item, final boolean empty) {
		super.updateItem(item, empty);

		if (item == null) return;
		if (getIndex() == -1) return;

		textField.setText(getIndex() + " " + item);
		setGraphic(textField);
		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	}


}
