package org.svenehrke.javafxdemos.table.tablepair;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class PersonTableCell extends TableCell<Person, TableCellItem> {

	private final Rectangle smallRect = new Rectangle(100, 30);
	private final Rectangle bigRect = new Rectangle(100, 60);
	private final TableViewState tableViewState;
	private final BooleanProperty bigProperty = new SimpleBooleanProperty();

	PersonTableCell(TableViewState tableViewState) {
		this.tableViewState = tableViewState;
		smallRect.setFill(Color.GREEN);
		bigRect.setFill(Color.RED);

		bigProperty.addListener((observable, oldValue, newValue) -> {
			System.out.printf("cell's bigProperty: %s -> %s%n", oldValue, newValue);
		});

		indexProperty().addListener((s,o,n) -> {
			if (getIndex() == -1) return;

			System.out.printf("getIndex() = %s -> %s%n", o, n);
			tableViewState.put(getIndex(), getTableColumn().getId(), this);

			bigProperty.bind(getTableView().getItems().get(n.intValue()).bigProperty());

		});

	}

	@Override
	protected void updateItem(final TableCellItem item, final boolean empty) {
		super.updateItem(item, empty);

		System.out.printf("PersonTableCell.updateItem: col: %s, idx: %s%n", getTableColumn().getId(), getIndex());


		if (item == null) return;
		if (getIndex() == -1) return;
		if (getTableRow() == null) return;

		Person p = getTableView().getItems().get(getIndex());
		System.out.printf("  p.getBig(): %s%n", p.getBig());

		boolean big = isBig(item.getValue());
//		item.setBig(big);
/*
		Platform.runLater(() -> {
			p.attributes().forEach(a -> {
				TableCellItem tci = a.getValue();
				if (tci != item) {
					if (tci._isBig() != big) {
						// put new item on other attribute so that 'updateItem' of that attributes column will be executed. Keep old string value but set newly calculated '_isBig':
						a.setValue(new TableCellItem(tci.getValue(), big));
					}
				}
			});
		});
*/


		Platform.runLater(() -> {
			if (p.getBig()) {
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
