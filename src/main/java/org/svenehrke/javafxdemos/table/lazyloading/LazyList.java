package org.svenehrke.javafxdemos.table.lazyloading;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.AbstractList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class LazyList<S> extends AbstractList<S> {
	private final int size;
	private final Function<Integer, S> itemProducer;
	private final BiConsumer<Integer, S> getAtConsumer;

	private final IntegerProperty fillSize = new SimpleIntegerProperty(0);

	private final Map<Integer, S> items = new HashMap<>();

	LazyList(final int size, final Function<Integer, S> itemProducer, BiConsumer<Integer, S> getAtConsumer) {
		this.size = size;
		this.itemProducer = itemProducer;
		this.getAtConsumer = getAtConsumer;
	}

	@Override
	public S get(final int index) {
		if (items.containsKey(index)) {
			System.out.println("map hit for index = " + index);
			return items.get(index);
		}

		System.out.println("creating item for rowIdx " + index + ". size: " + items.size());
		S item = itemProducer.apply(index);
		items.put(index, item);
		fillSize.setValue(fillSize.getValue() + 1);

		getAtConsumer.accept(index, item);

		return item;
	}

	@Override
	public int size() {
		return size;
	}

	public IntegerProperty fillSizeProperty() {
		return fillSize;
	}
}
