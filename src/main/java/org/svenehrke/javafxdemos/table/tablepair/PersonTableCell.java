package org.svenehrke.javafxdemos.table.tablepair;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableMap;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class PersonTableCell extends TableCell<Person, String> {

	private final TextField textField = new TextField();
	private final TextArea textArea = new TextArea();
	private final Rectangle rect = new Rectangle();

	private final HBox hBox = new HBox();
	private final TableViewState tableViewState;

	PersonTableCell(TableViewState tableViewState) {
		this.tableViewState = tableViewState;

		textArea.setWrapText(true);
		rect.setFill(Color.RED);
		rect.setWidth(2);
		rect.setHeight(2);
		hBox.getChildren().addAll(rect, textField);

		indexProperty().addListener((s,o,n) -> {
			int idx = n.intValue();
			if (idx < 0 || idx >= getTableView().getItems().size()) return;
			tableViewState.put(idx, getTableColumn().getId(), this);
		});
		tableRowProperty().addListener((s,o,n) -> {
			ObservableMap<Object,Object> map = getTableRow().getProperties();
			if (!map.containsKey(Constants.IDX_LISTENER)) {
				map.put(Constants.IDX_LISTENER, Boolean.TRUE);
				getTableRow().indexProperty().addListener((s2, o2, n2) -> {
					System.out.printf("*** row: %s -> %s%n", o2, n2);
					tableViewState.getRowSizeInfo(getIndex());
				});
			}
			if (!map.containsKey(Constants.HEIGHT_LISTENER)) {
				map.put(Constants.HEIGHT_LISTENER, Boolean.TRUE);
				getTableRow().heightProperty().addListener((s2, o2, n2) -> {
					if (Util.isDebugIndex(getIndex())) {
						System.out.printf("HEIGHT_LISTENER: table: %s, row: %s height: %s -> %s%n", getTableView().getId(), getIndex(), o2, n2);
					}
					Platform.runLater(() -> {
						RowSizeInfo rowSizeInfo = tableViewState.getRowSizeInfo(getIndex());
						DoubleProperty sp = Constants.LEFT_TV_ID.equals(getTableView().getId()) ? rowSizeInfo.rowSize1Property() : rowSizeInfo.rowSize2Property();
						sp.setValue(n2);
					});
				});
			}

		});
	}

	@Override
	protected void updateItem(final String item, final boolean empty) {
		super.updateItem(item, empty);

		if (item == null) return;
		if (getIndex() == -1) return;
		if (getTableRow() == null) return;

		textField.setText(getIndex() + " " + item);
		setGraphic(hBox);
		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

		if (getHeight() == 0.0) {
			return;
		}

		RowSizeInfo rowSizeInfo = tableViewState.getRowSizeInfo(getIndex());
		Double h2 = rowSizeInfo.rowSize1Property().getValue();
		double d = rowSizeInfo.rowSize1Property().doubleValue() - getHeight();
		if (Constants.COL_1_ID.equals(getTableColumn().getId()) && Util.isDebugIndex(getIndex())) {
			System.out.printf(String.format("UPD : row=%d, col=%s%n", getIndex(), getTableColumn().getId()));
			System.out.printf("     getHeight(): %s%n", getHeight());
			System.out.printf("     col: %s, row: %s, h2: %s, d: %s, hBox.height: %s%n", getTableColumn().getId(), getIndex(), h2, d, hBox.getHeight() );

		}
		if (h2 != -1) {
			Platform.runLater(() -> {
				if (Constants.COL_1_ID.equals(getTableColumn().getId())) {
					if (d != 0.0) {
						hBox.setPrefHeight(hBox.getHeight() + d);
					}
				}
				setGraphic(hBox);
			});
		}

	}


}
