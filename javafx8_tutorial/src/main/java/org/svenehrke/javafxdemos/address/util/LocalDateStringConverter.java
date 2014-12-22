package org.svenehrke.javafxdemos.address.util;

import javafx.util.StringConverter;

import java.time.LocalDate;

public class LocalDateStringConverter extends StringConverter<LocalDate> {
	@Override
	public String toString(LocalDate object) {
		return DateUtil.format(object);
	}

	@Override
	public LocalDate fromString(String string) {
		return DateUtil.parse(string);
	}
}
