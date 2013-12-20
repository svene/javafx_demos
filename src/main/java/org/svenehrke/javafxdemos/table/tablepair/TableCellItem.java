package org.svenehrke.javafxdemos.table.tablepair;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableCellItem {

	private StringProperty value;
	private boolean big = false;

	public TableCellItem(final String value, final boolean big) {
		this.value = new SimpleStringProperty(value);
		this.big = Person._isBig(value);
	}

	public String getValue() {
		return value.get();
	}

	public StringProperty valueProperty() {
		return value;
	}

	public boolean isBig() {
		return big;
	}

	public void setBig(final boolean big) {
		this.big = big;
	}
}
