package org.svenehrke.javafxdemos.address.util;

import javafx.util.StringConverter;

public class SimpleNumberStringConverter extends StringConverter<Number> {
	@Override
	public String toString(Number object) {
		return Integer.toString((Integer) object);
	}

	@Override
	public Number fromString(String string) {
		try {
			return Integer.parseInt(string);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
