package org.svenehrke.javafxdemos.minipm;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

class Attribute {
	private final String propertyName;
	private final Tag tag;
	private ObjectProperty value;

	static Attribute newValueAttribute(final String propertyName, final Object value) {
		return new Attribute(propertyName, value, Tag.VALUE);
	}
	Attribute(final String propertyName, final Object value, Tag tag) {
		this.propertyName = propertyName;
		this.tag = tag;
		this.value = new SimpleObjectProperty(value);
	}

	String getPropertyName() {
		return propertyName;
	}

	Tag getTag() {
		return tag;
	}

	ObjectProperty value() {
		return value;
	}
}
