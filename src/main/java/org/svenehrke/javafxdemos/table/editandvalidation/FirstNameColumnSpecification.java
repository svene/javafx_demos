package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

import java.util.function.Function;

class FirstNameColumnSpecification implements IColumnSpecification {

	Function<String, ValidationResult> validator = firstNameValidator();

	@Override
	public String title() {
		return "First Name";
	}

	@Override
	public Function<PersonTableBean, ValidatedString> validatedStringProvider() {
		return PersonTableBean::firstName;
	}

	@Override
	public StringConverter<String> convenienceConverter() {
		return new DefaultStringConverter();
	}

	@Override
	public String unformat(final String input) {
		return input;
	}

	@Override
	public String format(final String input) {
		return input;
	}

	@Override
	public Function<String, ValidationResult> validator() {
		return validator;
	}

	private static Function<String, ValidationResult> firstNameValidator() {
		return value -> {
			boolean valid = value.length() > 3;
			return new ValidationResult(valid, valid ? "" : "Length of first name must be greater than 3");
		};
	}
}
