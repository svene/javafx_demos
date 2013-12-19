package org.svenehrke.javafxdemos.table.tablepair;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Person {

	private final ObjectProperty<TableCellItem> name1;
	private final ObjectProperty<TableCellItem> name2;
	private final ObjectProperty<TableCellItem> name3;
	private final ObjectProperty<TableCellItem> name4;

	public Person(final int rowIdx) {
		this.name1 = newProperty(1, rowIdx);
		this.name2 = newProperty(2, rowIdx);
		this.name3 = newProperty(3, rowIdx);
		this.name4 = newProperty(4, rowIdx);
	}

	private ObjectProperty<TableCellItem> newProperty(int colIdx, final int rowIdx) {
		TableCellItem tci = new TableCellItem("name " + colIdx + " / " + rowIdx, false);
		return new SimpleObjectProperty<>(tci);
	}

	public ObjectProperty<TableCellItem> name1Property() {
		return name1;
	}

	public ObjectProperty<TableCellItem> name2Property() {
		return name2;
	}

	public ObjectProperty<TableCellItem> name3Property() {
		return name3;
	}

	public ObjectProperty<TableCellItem> name4Property() {
		return name4;
	}

}
