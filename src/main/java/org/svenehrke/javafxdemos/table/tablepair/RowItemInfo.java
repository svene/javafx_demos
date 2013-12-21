package org.svenehrke.javafxdemos.table.tablepair;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class RowItemInfo {

	public static final String BIG = "big";
	private final Map<String, Function<StringProperty, Boolean>> mappers = new HashMap<>();
	private final Map<String, BooleanProperty> properties = new HashMap<>();

	public RowItemInfo() {
		properties.put(BIG, new SimpleBooleanProperty());
	}

	public void addProperty(String key, Function<StringProperty, Boolean> mapper) {
		this.mappers.put(key, mapper);
	}

	public Map<String, BooleanProperty> getProperties() {
		return properties;
	}

	public static boolean isBig(final String item) {
		return item != null && item.length() > 17;
	}

	public void bind(final Person person) {
		BooleanProperty big = properties.get(BIG);
		ChangeListener<String> listener = (s,o,n) -> {
			Function<StringProperty, Boolean> m = mappers.get(BIG);
			big.setValue(person.attributes().stream().map(m).reduce((b1, b2) -> b1 || b2).get());
		};
		person.attributes().forEach(a -> a.addListener(listener));

		big.addListener((s,o,n) -> {
			System.out.printf("Person.big: %s -> %s%n", o, n);

			// Force change notification so that 'TableCell.updateItem() is triggered:
//			person.attributes().forEach(a -> ((SetWithNotificationAble) a).setWithNotification(a.getValue()));
			person.attributes().forEach(a -> ((Fireable)a).fireValueChangedEvent());
		});

	}


}
