package org.svenehrke.javafxdemos.table.customtablecell;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {

	private final StringProperty name1;
	private final StringProperty name2;

	public Person(final int rowIdx) {
		this.name1 = newProperty(1, rowIdx);
		this.name2 = newProperty(2, rowIdx);
	}

	private StringProperty newProperty(int colIdx, final int rowIdx) {
		return new SimpleStringProperty("name " + colIdx + " / " + rowIdx);
	}

	public StringProperty name1Property() {
		return name1;
	}

	public StringProperty name2Property() {
		return name2;
	}


}
