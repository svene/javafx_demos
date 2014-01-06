package org.svenehrke.javafxdemos.table.tablepair;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
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

		tableRowProperty().addListener((s,o,n) -> {

			ObservableMap<Object,Object> map = getTableRow().getProperties();
			if (!map.containsKey(Constants.HEIGHT_LISTENER)) {
				map.put(Constants.HEIGHT_LISTENER, Boolean.TRUE);
				getTableRow().heightProperty().addListener((s2, o2, n2) -> {
					if (Util.isDebugIndex(getIndex())) {
						System.out.printf("HEIGHT_LISTENER: table: %s, row: %s height: %s -> %s%n", getTableView().getId(), getIndex(), o2, n2);
					}
					if (n2.doubleValue() > 0 && getIndex() > 0 && getIndex() < getTableView().getItems().size()) {
						Platform.runLater(() -> {
							RowSizeInfo rowSizeInfo = tableViewState.getRowSizeInfo(getIndex());
							rowSizeInfo.rowSize1Property().setValue(n2); // other table's RowSizeInfo
						});
					}
				});
			}

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
			System.out.println("UPD: button col, idx: " + getIndex());
		}

		int newHeight = Person.isLongText(item) ? 60 : 30;

		Platform.runLater(() -> {
			button.setText(item);
			if (Util.isDebugIndex(getIndex())) {
				System.out.printf("--> button.setPrefHeight(%d)%n", newHeight);
			}
			button.setPrefHeight(newHeight);
			setGraphic(button);
		});

		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	}




}
