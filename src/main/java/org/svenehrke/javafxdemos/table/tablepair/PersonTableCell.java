package org.svenehrke.javafxdemos.table.tablepair;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class PersonTableCell extends TableCell<TableViewPairDemo.Person, String> {
	private final Rectangle smallRect = new Rectangle(100, 30);
	private final Rectangle bigRect = new Rectangle(100, 60);
	private final TableViewState tableViewState;

	PersonTableCell(TableViewState tableViewState) {
		this.tableViewState = tableViewState;
		smallRect.setFill(Color.GREEN);
		bigRect.setFill(Color.RED);

		indexProperty().addListener((s,o,n) -> {
			if (getIndex() == -1) return;

			System.out.printf("getIndex() = %s -> %s%n", o, n);
			tableViewState.put(getIndex(), getTableColumn().getId(), this);
		});

	}

	@Override
	protected void updateItem(final String item, final boolean empty) {
		super.updateItem(item, empty);
		System.out.printf("PersonTableCell.updateItem: col: %s, idx: %s%n", getTableColumn().getId(), getIndex());


		if (item == null) return;
		if (getIndex() == -1) return;
		if (getTableRow() == null) return;

		TableViewPairDemo.Person p = (TableViewPairDemo.Person) getTableRow().getItem();
		p.name1Property().setValue(item);
		p.name2Property().setValue(item);
		p.name4Property().setValue(item);

		Boolean bigRow = isBig(item);

		Platform.runLater(() -> {
			if (bigRow) {
				setGraphic(bigRect);
			}
			else {
				setGraphic(smallRect);
			}
		});

		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	}

	private boolean isBig(final String item) {
		return item.length() > 12;
	}
}
