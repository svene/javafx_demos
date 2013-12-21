package org.svenehrke.javafxdemos.table.tablepair;

import javafx.beans.property.StringProperty;

import java.util.Arrays;

public class Person {

	private final BetterStringProperty name1 = new BetterStringProperty();
	private final BetterStringProperty name2 = new BetterStringProperty();
	private final BetterStringProperty name3 = new BetterStringProperty();
	private final BetterStringProperty name4 = new BetterStringProperty();

	public static final String LONG_TEXT = "long_text";
	private final RowItemInfo rowItemInfo = new RowItemInfo();


	public Person(final int rowIdx) {
		name1.setValue(valueFrom(1, rowIdx));
		name2.setValue(valueFrom(2, rowIdx));
		name3.setValue(valueFrom(3, rowIdx));
		name4.setValue(valueFrom(4, rowIdx));

		rowItemInfo.addProperty(LONG_TEXT, a -> isLongText(a.getValue()), (b1, b2) -> b1 || b2);
		rowItemInfo.bind(Arrays.asList(name1, name2, name3, name4));
	}

	private static boolean isLongText(final String item) {
		return item != null && item.length() > 17;
	}

	public RowItemInfo getRowItemInfo() {
		return rowItemInfo;
	}

	public StringProperty name1Property() {
		return name1;
	}

	public StringProperty name2Property() {
		return name2;
	}

	public StringProperty name3Property() {
		return name3;
	}

	public StringProperty name4Property() {
		return name4;
	}

	private String valueFrom(final int colIdx, final int rowIdx) {
		return "name " + colIdx + " / " + rowIdx;
	}


}
