package org.svenehrke.javafxdemos.table.tablepair;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class RowSizeInfo {

/*
	private final DoubleProperty rowSize = new SimpleDoubleProperty();
	private final Function<StringProperty, Double> mapper;
	private final BinaryOperator<Double> reducer;

	public void addProperty(String key, Function<StringProperty, Double> mapper, BinaryOperator<Double> reducer) {
		this.mapper = mapper;
		this.reducers.put(key, reducer);
	}

	public Map<String, DoubleProperty> getProperties() {
		return properties;
	}

	public <T extends StringProperty & Fireable> void bind(List<T> attributes) {

		// Listen on each attribute for change. In case of a attribute change change row property:
		attributes.forEach(a -> a.addListener((ChangeListener<String>) (s, o, n) -> {
			rowSize.setValue(attributes.stream().map(mappers.get(key)).reduce(reducers.get(key)).get()));
		}));

		// In case of a row property change fire ValueChangeEvent on all attributes:
		properties.values().forEach(property -> property.addListener((s,o,n) -> {
			// Force change notification so that 'TableCell.updateItem() is triggered even when the value is the same:
			attributes.forEach(a -> a.fireValueChangedEvent());
		}));

	}
*/

}
