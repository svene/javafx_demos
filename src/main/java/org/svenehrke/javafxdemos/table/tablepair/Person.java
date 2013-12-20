package org.svenehrke.javafxdemos.table.tablepair;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;

import java.util.Arrays;
import java.util.List;

public class Person {

	private final StringProperty name1;
	private final StringProperty name2;
	private final StringProperty name3;
	private final StringProperty name4;

	private final BooleanProperty big;

	public Person(final int rowIdx) {
		this.name1 = newProperty(1, rowIdx);
		this.name2 = newProperty(2, rowIdx);
		this.name3 = newProperty(3, rowIdx);
		this.name4 = newProperty(4, rowIdx);

		big = new SimpleBooleanProperty();

		ChangeListener<String> listener = (s,o,n) -> {
			big.setValue(_isBig(name1.getValue()) || _isBig(name2.getValue()) || _isBig(name3.getValue()) || _isBig(name4.getValue()) );
		};
		attributes().forEach(a -> a.addListener(listener));

		big.addListener((s,o,n) -> {
			System.out.printf("Person.big: %s -> %s%n", o, n);

			// Force change notification:
			attributes().forEach(a -> {
				String old = a.getValue();
				a.setValue(old + "x");
			});
		});
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

	public StringProperty name3Property() {
		return name3;
	}

	public StringProperty name4Property() {
		return name4;
	}

	public boolean getBig() {
		return big.get();
	}

	public BooleanProperty bigProperty() {
		return big;
	}

	public List<StringProperty> attributes() {
		return Arrays.asList(name1, name2, name3, name4);
	}

	public static boolean _isBig(final String item) {
		return item != null && item.length() > 17;
	}

}
