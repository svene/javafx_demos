package org.svenehrke.javafxdemos.table.tablepair;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class RowItemInfo {

	private final Map<String, BooleanProperty> properties = new HashMap<>();
	private final Map<String, Function<StringProperty, Boolean>> mappers = new HashMap<>();
	private final Map<String, BinaryOperator<Boolean>> reducers = new HashMap<>();

	public void addProperty(String key, Function<StringProperty, Boolean> mapper, BinaryOperator<Boolean> reducer) {
		properties.put(key, new SimpleBooleanProperty());
		this.mappers.put(key, mapper);
		this.reducers.put(key, reducer);
	}

	public Map<String, BooleanProperty> getProperties() {
		return properties;
	}

	public <T extends StringProperty & Fireable> void bind(List<T> attributes) {

		// Listen on each attribute for change. In case of a attribute change change row property:
		attributes.forEach(a -> a.addListener((ChangeListener<String>) (s, o, n) -> {
			properties.keySet().forEach(key -> properties.get(key).setValue(attributes.stream().map(mappers.get(key)).reduce(reducers.get(key)).get()));
		}));

		// In case of a row property change fire ValueChangeEvent on all attributes:
		properties.values().forEach(property -> property.addListener((s,o,n) -> {
			// Force change notification so that 'TableCell.updateItem() is triggered even when the value is the same:
			attributes.forEach(a -> a.fireValueChangedEvent());
		}));

	}

}
