package org.svenehrke.javafxdemos.table.tablepair;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.util.Pair;
import org.svenehrke.javafxdemos.common.TableViews;

class ButtonTableCell extends TableCell<Person, String> {

	private final Button button;

	ButtonTableCell(TableViewState tableViewState) {
		button = new Button();


		indexProperty().addListener((s,o,n) -> {
			int idx = n.intValue();
			if (idx < 0 || idx >= getTableView().getItems().size()) return;

			tableViewState.put(idx, getTableColumn().getId(), this);

			Pair<Integer, Integer> visibleRange = TableViews.getVisibleRange(getTableView());
/*
			System.out.printf("button col: idx: %s, rowrange: %d-%d%n", getIndex(), visibleRange.getKey(), visibleRange.getValue());
			Platform.runLater(() -> {
				Pair<Integer, Integer> visibleRange2 = TableViews.getVisibleRange(getTableView());
				System.out.printf("--> button col: rowrange: %d-%d%n", visibleRange2.getKey(), visibleRange2.getValue());

			});
*/

		});

//		indexProperty().addListener((s,o,n) -> {
//			System.out.println("!!!!! row: " + getIndex());
//		});

		tableRowProperty().addListener((s,o,n) -> {

			ObservableMap<Object,Object> map = getTableRow().getProperties();

			if (!map.containsKey(Constants.IDX_LISTENER)) {
				map.put(Constants.IDX_LISTENER, Boolean.TRUE);
				getTableRow().indexProperty().addListener((s2, o2, n2) -> {
					System.out.printf(getTableView().getId() + ": ===== row: %s -> %s, height: %s%n", o2, n2, getTableRow().getHeight());
					if (getIndex() < 0 || getIndex() >= getTableView().getItems().size()) return;
					tableViewState.getRowSizeInfo(getIndex());

					RowSizeInfo rowSizeInfo = tableViewState.getRowSizeInfo(getIndex());
					rowSizeInfo.rowSize1Property().setValue(getTableRow().getHeight()); // set other table's RowSizeInfo
				});
			}

			if (!map.containsKey(Constants.HEIGHT_LISTENER)) {
				map.put(Constants.HEIGHT_LISTENER, Boolean.TRUE);
				getTableRow().heightProperty().addListener((s2, o2, n2) -> {
/*
					getTableRow().getChildrenUnmodifiable().forEach(cell -> {
						TableCell tc = (TableCell) cell;
						System.out.printf("row: %s, height: %s, cell.prefHeight(-1): %s, cell.prefHeight: %s, cell.getHeight: %s%n", getIndex(), getTableRow().getHeight(), tc.prefHeight(-1), tc.getPrefHeight(), tc.getHeight());
					});
*/
/*
					Platform.runLater(() -> {
						getTableRow().getChildrenUnmodifiable().forEach(cell -> {
							TableCell tc = (TableCell) cell;
							System.out.printf("row: %s, height: %s, cell.prefHeight(-1): %s, cell.prefHeight: %s, cell.getHeight: %s%n", getIndex(), getTableRow().getHeight(), tc.prefHeight(-1), tc.getPrefHeight(), tc.getHeight());
						});
					});
*/

					if (Util.isDebugIndex(getIndex())) {
						System.out.printf("HEIGHT_LISTENER: table: %s, row: %s height: %s -> %s%n", getTableView().getId(), getIndex(), o2, n2);
					}
					if (n2.doubleValue() > 0 && getIndex() >= 0 && getIndex() < getTableView().getItems().size()) {
						Platform.runLater(() -> {
							RowSizeInfo rowSizeInfo = tableViewState.getRowSizeInfo(getIndex());
							rowSizeInfo.rowSize1Property().setValue(n2); // set other table's RowSizeInfo
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
		});

		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	}




}
