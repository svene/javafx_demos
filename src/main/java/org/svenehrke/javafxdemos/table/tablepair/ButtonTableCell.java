package org.svenehrke.javafxdemos.table.tablepair;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.svenehrke.javafxdemos.common.TableViews;

class ButtonTableCell extends TableCell<Person, String> {

	private final Button button;

	ButtonTableCell(TableViewState tableViewState, final boolean transparent) {
		button = new Button();
		if (transparent) {
			button.setVisible(false);
			button.setMaxWidth(0);
			setMaxWidth(0);
		}

		indexProperty().addListener((s,o,n) -> {
			int idx = n.intValue();
			if (idx < 0 || idx >= getTableView().getItems().size()) return;

			tableViewState.put(idx, getTableColumn().getId(), this);

		});

	}

	@Override
	protected void updateItem(final String item, final boolean empty) {
		super.updateItem(item, empty);

//		System.out.printf("PersonTableCell.updateItem: col: %s, idx: %s, item: %s (%s)%n", getTableColumn().getId(), getIndex(), item, (item == null ? "-" : item.length()));

		if (item == null) return;
		if (getIndex() == -1) return;
		if (getTableRow() == null) return;

		if (Util.isDebugIndex(getIndex())) {
			System.out.println("BUTTON UPD: button col, idx: " + getIndex());
		}

		int newHeight = Person.isLongText(item) ? 60 : 30;

		Platform.runLater(() -> {
			button.setText(getIndex() + " " + item);
			if (Util.isDebugIndex(getIndex())) {
				System.out.printf("--> button.setPrefHeight(%d)%n", newHeight);
			}
			button.setPrefHeight(newHeight);
			setGraphic(button);
//			Person p = (Person) getTableRow().getItem();
//			p.setHeight(newHeight);
//			Util.asFireable(p.name1Property()).fireValueChangedEvent();
		});

		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	}




}
