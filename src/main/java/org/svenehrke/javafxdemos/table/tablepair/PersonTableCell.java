package org.svenehrke.javafxdemos.table.tablepair;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class PersonTableCell extends TableCell<Person, String> {

	private final Rectangle smallRect = new Rectangle(100, 30);
	private final Rectangle bigRect = new Rectangle(100, 60);

	private final TextField textField = new TextField();
	private final TextArea textArea = new TextArea();

	private final HBox hBox = new HBox();

	PersonTableCell(TableViewState tableViewState) {
		smallRect.setFill(Color.GREEN);
		bigRect.setFill(Color.RED);

		textArea.setWrapText(true);

		hBox.getChildren().addAll(textField);

		indexProperty().addListener((s,o,n) -> {
			int idx = n.intValue();
			if (idx < 0 || idx >= getTableView().getItems().size()) return;

			System.out.printf("getIndex() = %s -> %s%n", o, n);
			tableViewState.put(idx, getTableColumn().getId(), this);

		});

	}

	@Override
	protected void updateItem(final String item, final boolean empty) {
		super.updateItem(item, empty);

		System.out.printf("PersonTableCell.updateItem: col: %s, idx: %s, item: %s (%s)%n", getTableColumn().getId(), getIndex(), item, (item == null ? "-" : item.length()));

		if (item == null) return;
		if (getIndex() == -1) return;
		if (getTableRow() == null) return;

		Person p = getTableView().getItems().get(getIndex());

		boolean longText = p.getRowItemInfo().getProperties().get(Person.LONG_TEXT).get();
		System.out.printf("  longText: %s%n", longText);

		textField.setText(item);
		textArea.setText(item);
		Platform.runLater(() -> {
			hBox.setPrefHeight(longText ? 60 : 30);
			setGraphic(hBox);
		});

		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	}

}
