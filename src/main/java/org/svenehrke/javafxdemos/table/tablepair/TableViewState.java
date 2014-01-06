package org.svenehrke.javafxdemos.table.tablepair;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;

import java.util.HashMap;
import java.util.Map;

public class TableViewState {

	private final ObservableList<Person> items;
	private Map<Integer, Map<String, TableCell>> cellsByRow = new HashMap();

	public TableViewState(final ObservableList<Person> items) {

		this.items = items;
	}

	public void put(Integer rowIdx, String columnId, TableCell tableCell) {
		if (rowIdx < 0) return;
		if (columnId == null) return;
		Map<String, TableCell> tableCells = cellsByRow.get(rowIdx);
		if (tableCells == null) {
			tableCells = new HashMap<>();
			cellsByRow.put(rowIdx, tableCells);
		}
		tableCells.put(columnId, tableCell);
	}

	public ObservableList<Person> getItems() {
		return items;
	}

	public TableCell get(Integer rowIdx, String columnId) {
		if (rowIdx < 0) return null;
		if (columnId == null) return null;

		Map<String, TableCell> tableCells = cellsByRow.get(rowIdx);
		if (tableCells == null) return null;
		return tableCells.get(columnId);
	}

}
