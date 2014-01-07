package org.svenehrke.javafxdemos.common;

import javafx.beans.property.SimpleObjectProperty;

public class FireableSimpleObjectProperty<T> extends SimpleObjectProperty<T> {

	public FireableSimpleObjectProperty() {
		super();
	}
	public FireableSimpleObjectProperty(final T tci) {
		super(tci);
	}

	@Override
	public void fireValueChangedEvent() {
		super.fireValueChangedEvent();
	}
}
