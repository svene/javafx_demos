package org.svenehrke.javafxdemos.table.tablepair;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class PersonTableCell extends TableCell<TableViewPairDemo.Person, String> {
	private final TextArea textArea = new TextArea();
	private final Rectangle smallRect = new Rectangle(100, 30);
	private final Rectangle bigRect = new Rectangle(100, 60);

	PersonTableCell() {
		textArea.setWrapText(true);
		textArea.setPrefHeight(30);
		smallRect.setFill(Color.GREEN);
		bigRect.setFill(Color.RED);
	}

	@Override
	protected void updateItem(final String item, final boolean empty) {
		super.updateItem(item, empty);
		if (item == null) return;
		int h = h(item);
		textArea.setPrefHeight(h);
		textArea.setText(item);
		setGraphic(isBig(item) ? bigRect : smallRect);

		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	}

	private int h(final String item) {
		return item.length() > 12 ? 50 : 30;
	}
	private boolean isBig(final String item) {
		return item.length() > 12;
	}
}
