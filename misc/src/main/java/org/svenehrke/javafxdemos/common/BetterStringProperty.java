package org.svenehrke.javafxdemos.common;

import javafx.beans.property.SimpleStringProperty;

class BetterStringProperty extends SimpleStringProperty implements Fireable, SetWithNotificationAble {
	public BetterStringProperty() {
	}

	public BetterStringProperty(final String initialValue) {
		super(initialValue);
	}

	public BetterStringProperty(final Object bean, final String name) {
		super(bean, name);
	}

	public BetterStringProperty(final Object bean, final String name, final String initialValue) {
		super(bean, name, initialValue);
	}

	@Override
	public void fireValueChangedEvent() {
		super.fireValueChangedEvent();
	}

	@Override
	public void setWithNotification(final String v) {
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

}
