package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.util.StringConverter;

class ToUpperCaseStringConverter extends StringConverter<String> {
	@Override
	public String toString(final String value) {
		return (value != null) ? value : "";
	}

	@Override
	public String fromString(final String string) {
		return string.toUpperCase();
	}
}
