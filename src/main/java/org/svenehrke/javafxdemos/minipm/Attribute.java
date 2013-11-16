package org.svenehrke.javafxdemos.minipm;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class Attribute {
	private StringProperty value;

	Attribute(final String value) {
		this.value = new SimpleStringProperty(value);
	}

	StringProperty value() {
		return value;
	}
}
