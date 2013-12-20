package org.svenehrke.javafxdemos.table.tablepair;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class PersonTableCell extends TableCell<Person, String> {

	private final Rectangle smallRect = new Rectangle(100, 30);
	private final Rectangle bigRect = new Rectangle(100, 60);
	private final BooleanProperty bigProperty = new SimpleBooleanProperty();

	PersonTableCell(TableViewState tableViewState) {
		smallRect.setFill(Color.GREEN);
		bigRect.setFill(Color.RED);

		bigProperty.addListener((observable, oldValue, newValue) -> {
			System.out.printf("cell's bigProperty: %s -> %s%n", oldValue, newValue);
		});

		indexProperty().addListener((s,o,n) -> {
			int idx = n.intValue();
			if (idx < 0 || idx >= getTableView().getItems().size()) return;

			System.out.printf("getIndex() = %s -> %s%n", o, n);
			tableViewState.put(idx, getTableColumn().getId(), this);

			bigProperty.bind(getTableView().getItems().get(idx).bigProperty());

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
		System.out.printf("  p.getBig(): %s%n", p.getBig());

		Platform.runLater(() -> setGraphic(p.getBig() ? bigRect : smallRect));

		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	}

}
