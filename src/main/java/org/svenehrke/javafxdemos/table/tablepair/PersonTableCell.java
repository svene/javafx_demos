package org.svenehrke.javafxdemos.table.tablepair;

import javafx.application.Platform;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class PersonTableCell extends TableCell<TableViewPairDemo.Person, String> {
	private final Rectangle smallRect = new Rectangle(100, 30);
	private final Rectangle bigRect = new Rectangle(100, 60);

	PersonTableCell() {
		smallRect.setFill(Color.GREEN);
		bigRect.setFill(Color.RED);
	}

	@Override
	protected void updateItem(final String item, final boolean empty) {
		super.updateItem(item, empty);
		if (item == null) return;
		Platform.runLater(() -> setGraphic(isBig(item) ? bigRect : smallRect));

		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	}

	private boolean isBig(final String item) {
		return item.length() > 12;
	}
}
