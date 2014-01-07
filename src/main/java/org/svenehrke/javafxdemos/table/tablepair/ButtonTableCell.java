package org.svenehrke.javafxdemos.table.tablepair;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;

class ButtonTableCell extends TableCell<Person, String> {

	private final Button button;

	ButtonTableCell(TableViewState tableViewState) {
		button = new Button();

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
		if (getTableRow() == null) return;

		int newHeight = Person.isLongText(item) ? 60 : 30;

		Platform.runLater(() -> {
			button.setText(getIndex() + " " + item);
			button.setPrefHeight(newHeight);
			setGraphic(button);
		});

		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	}

}
