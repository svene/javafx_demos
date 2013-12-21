package org.svenehrke.javafxdemos.table.tablepair;

import javafx.beans.property.StringProperty;

import java.util.Arrays;
import java.util.List;

public class Person {

	private final StringProperty name1 = new BetterStringProperty();
	private final StringProperty name2 = new BetterStringProperty();
	private final StringProperty name3 = new BetterStringProperty();
	private final StringProperty name4 = new BetterStringProperty();

	public static final String BIG = "big";
	private final RowItemInfo rowItemInfo = new RowItemInfo();


	public Person(final int rowIdx) {
		name1.setValue(valueFrom(1, rowIdx));
		name2.setValue(valueFrom(2, rowIdx));
		name3.setValue(valueFrom(3, rowIdx));
		name4.setValue(valueFrom(4, rowIdx));

		rowItemInfo.addProperty(BIG, a -> isBig(a.getValue()), (b1, b2) -> b1 || b2);
		rowItemInfo.bind(this.attributes());
	}

	public static boolean isBig(final String item) {
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

	public List<StringProperty> attributes() {
		return Arrays.asList(name1, name2, name3, name4);
	}

	private String valueFrom(final int colIdx, final int rowIdx) {
		return "name " + colIdx + " / " + rowIdx;
	}


}
