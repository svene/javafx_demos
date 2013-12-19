package org.svenehrke.javafxdemos.table.tablepair;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TableCell;

import java.util.HashMap;
import java.util.Map;

public class TableViewState {

	private Map<Integer, Map<String, TableCell>> cellsByRow = new HashMap();
	private Map<Integer, BooleanProperty> bigRows = new HashMap();

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

	public TableCell get(Integer rowIdx, String columnId) {
		if (rowIdx < 0) return null;
		if (columnId == null) return null;

		Map<String, TableCell> tableCells = cellsByRow.get(rowIdx);
		if (tableCells == null) return null;
		return tableCells.get(columnId);
	}

	public BooleanProperty getBigRowProperty(Integer rowIdx) {
		if (!bigRows.containsKey(rowIdx)) {
			setBigRow(rowIdx, false);
		}
		return bigRows.get(rowIdx);
	}

	public void setBigRow(Integer rowIdx, boolean isBig) {
		BooleanProperty booleanProperty = bigRows.get(rowIdx);
		if (booleanProperty == null) {
			bigRows.put(rowIdx, booleanProperty = new SimpleBooleanProperty(isBig));
		}
		booleanProperty.setValue(isBig);
	}

	public Boolean isBigRow(Integer rowIdx) {
		return (bigRows.get(rowIdx) != null) && bigRows.get(rowIdx).getValue();
	}
}
