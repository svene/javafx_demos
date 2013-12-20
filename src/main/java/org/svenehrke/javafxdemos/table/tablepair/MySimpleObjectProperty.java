package org.svenehrke.javafxdemos.table.tablepair;

import javafx.beans.property.SimpleObjectProperty;

public class MySimpleObjectProperty<T> extends SimpleObjectProperty<T> {

	public MySimpleObjectProperty(final T tci) {
		super(tci);
	}

	@Override
	public void fireValueChangedEvent() {
		super.fireValueChangedEvent();
	}
}
