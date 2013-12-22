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

		rowItemInfo.addProperty(LONG_TEXT, a -> isLongText(a.getValue()), (b1, b2) -> b1 || b2);
		rowItemInfo.bind(Arrays.asList(name1, name2, name3, name4));

		name1.setValue(valueFrom(1, rowIdx));
		name2.setValue(valueFrom(2, rowIdx));
		name3.setValue(valueFrom(3, rowIdx));
		name4.setValue(valueFrom(4, rowIdx));

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
		String s = "";
		s = String.format("%d %030d", rowIdx, 0);
		if (colIdx == 1) {
			if (rowIdx % 3 == 0) {
				return s;
			}
		}
		else if (colIdx == 3) {
			if (rowIdx % 5 == 0) {
				return s;
			}
		}
		else if (colIdx == 2) {
			if (rowIdx % 4 == 0) {
				return s;
			}
		}
		else if (colIdx == 4) {
			if (rowIdx % 7 == 0) {
				return s;
			}
		}
		return "name " + colIdx + " / " + rowIdx;
	}


}
