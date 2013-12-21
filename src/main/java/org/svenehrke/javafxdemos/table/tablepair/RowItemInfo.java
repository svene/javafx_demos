package org.svenehrke.javafxdemos.table.tablepair;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class RowItemInfo {

	private final Map<String, Function<StringProperty, Boolean>> mappers = new HashMap<>();
	private final Map<String, BooleanProperty> properties = new HashMap<>();

	public void addProperty(String key, Function<StringProperty, Boolean> mapper) {
		this.mappers.put(key, mapper);
		properties.put(key, new SimpleBooleanProperty());
	}

	public Map<String, BooleanProperty> getProperties() {
		return properties;
	}

	public void bind(final Person person) {
		for (String key : mappers.keySet()) {
			BooleanProperty property = properties.get(key);
			person.attributes().forEach(a -> a.addListener((ChangeListener<String>) (s,o,n) -> property.setValue(person.attributes().stream().map(mappers.get(key)).reduce((b1, b2) -> b1 || b2).get())));

			property.addListener((s,o,n) -> {
				// Force change notification so that 'TableCell.updateItem() is triggered:
//				person.attributes().forEach(a -> ((SetWithNotificationAble) a).setWithNotification(a.getValue()));
				person.attributes().forEach(a -> ((Fireable)a).fireValueChangedEvent());
			});

		}

	}


}
