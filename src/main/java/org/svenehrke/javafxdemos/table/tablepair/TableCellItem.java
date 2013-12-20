package org.svenehrke.javafxdemos.table.tablepair;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableCellItem {

	private StringProperty value;

	public TableCellItem(final String value) {
		this.value = new SimpleStringProperty(value);
	}

	public String getValue() {
		return value.get();
	}


}
