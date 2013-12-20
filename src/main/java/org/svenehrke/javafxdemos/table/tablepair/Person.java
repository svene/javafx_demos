package org.svenehrke.javafxdemos.table.tablepair;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;

import java.util.Arrays;
import java.util.List;

public class Person {

	private final ObjectProperty<String> name1 = newStringProperty();
	private final ObjectProperty<String> name2 = newStringProperty();
	private final ObjectProperty<String> name3 = newStringProperty();
	private final ObjectProperty<String> name4 = newStringProperty();

	private final BooleanProperty big;

	public static ObjectProperty<String> newStringProperty() {

		return new SimpleObjectProperty<String>() {

			@Override
			public void set(final String v) {
				if (v == null && getValue() == null) {
					fireValueChangedEvent();
				}
				else if (v.equals(getValue())) {
					fireValueChangedEvent();
				}
				else {
					super.set(v);
				}

			}
		};
	}

	public Person(final int rowIdx) {
		name1.setValue(valueFrom(1, rowIdx));
		name2.setValue(valueFrom(2, rowIdx));
		name3.setValue(valueFrom(3, rowIdx));
		name4.setValue(valueFrom(4, rowIdx));

		big = new SimpleBooleanProperty();

		ChangeListener<String> listener = (s,o,n) -> {
			big.setValue(_isBig(name1.getValue()) || _isBig(name2.getValue()) || _isBig(name3.getValue()) || _isBig(name4.getValue()) );
		};
		attributes().forEach(a -> a.addListener(listener));

		big.addListener((s,o,n) -> {
			System.out.printf("Person.big: %s -> %s%n", o, n);

			// Force change notification:
			attributes().forEach(a -> a.setValue(a.getValue()));
		});
	}

	private String valueFrom(final int colIdx, final int rowIdx) {
		return "name " + colIdx + " / " + rowIdx;
	}

	public ObjectProperty<String> name1Property() {
		return name1;
	}

	public ObjectProperty<String> name2Property() {
		return name2;
	}

	public ObjectProperty<String> name3Property() {
		return name3;
	}

	public ObjectProperty<String> name4Property() {
		return name4;
	}

	public boolean getBig() {
		return big.get();
	}

	public BooleanProperty bigProperty() {
		return big;
	}

	public List<ObjectProperty<String>> attributes() {
		return Arrays.asList(name1, name2, name3, name4);
	}

	public static boolean _isBig(final String item) {
		return item != null && itemValue(item).length() > 17;
	}

	private static String itemValue(String item) {
		int idx = item.indexOf('|');
		return idx == -1 ? item : item.substring(idx + 1);
	}

}
