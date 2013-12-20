package org.svenehrke.javafxdemos.table.tablepair;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;

import java.util.Arrays;
import java.util.List;

public class Person {

	private final StringProperty name1 = new BetterStringProperty();
	private final StringProperty name2 = new BetterStringProperty();
	private final StringProperty name3 = new BetterStringProperty();
	private final StringProperty name4 = new BetterStringProperty();

	private final BooleanProperty big;

	public Person(final int rowIdx) {
		name1.setValue(valueFrom(1, rowIdx));
		name2.setValue(valueFrom(2, rowIdx));
		name3.setValue(valueFrom(3, rowIdx));
		name4.setValue(valueFrom(4, rowIdx));

		big = new SimpleBooleanProperty();

		ChangeListener<String> listener = (s,o,n) -> {
			big.setValue(isBig(name1.getValue()) || isBig(name2.getValue()) || isBig(name3.getValue()) || isBig(name4.getValue()) );
		};
		attributes().forEach(a -> a.addListener(listener));

		big.addListener((s,o,n) -> {
			System.out.printf("Person.big: %s -> %s%n", o, n);

			// Force change notification so that 'TableCell.updateItem() is triggered:
//			attributes().forEach(a -> ((SetWithNotificationAble) a).setWithNotification(a.getValue()));
			attributes().forEach(a -> ((Fireable)a).fireValueChangedEvent());
		});
	}

	private String valueFrom(final int colIdx, final int rowIdx) {
		return "name " + colIdx + " / " + rowIdx;
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

	public List<StringProperty> attributes() {
		return Arrays.asList(name1, name2, name3, name4);
	}

	public static boolean isBig(final String item) {
		return item != null && itemValue(item).length() > 17;
	}

	private static String itemValue(String item) {
		int idx = item.indexOf('|');
		return idx == -1 ? item : item.substring(idx + 1);
	}

}
