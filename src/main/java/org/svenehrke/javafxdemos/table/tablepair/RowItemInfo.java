package org.svenehrke.javafxdemos.table.tablepair;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;

public class RowItemInfo {

	private final BooleanProperty big;

	public RowItemInfo() {
		big = new SimpleBooleanProperty();
	}

	public void bind(final Person person) {
		ChangeListener<String> listener = (s,o,n) -> {
			big.setValue(
				Person.isBig(person.name1Property().getValue())
				|| Person.isBig(person.name2Property().getValue())
				|| Person.isBig(person.name3Property().getValue())
				|| Person.isBig(person.name4Property().getValue())
			);
		};
		person.attributes().forEach(a -> a.addListener(listener));

		big.addListener((s,o,n) -> {
			System.out.printf("Person.big: %s -> %s%n", o, n);

			// Force change notification so that 'TableCell.updateItem() is triggered:
//			person.attributes().forEach(a -> ((SetWithNotificationAble) a).setWithNotification(a.getValue()));
			person.attributes().forEach(a -> ((Fireable)a).fireValueChangedEvent());
		});

	}

	public boolean getBig() {
		return big.get();
	}

}
