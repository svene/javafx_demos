package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.util.function.Function;

class BigDecimalColumnSpecification implements IColumnSpecification {

	Function<String, ValidationResult> validator = validator(this);

	@Override
	public String title() {
		return "BigDecimal";
	}

	@Override
	public Function<PersonTableBean, ValidatedString> validatedStringProvider() {
		return PersonTableBean::bigDecimalValue;
	}

	@Override
	public StringConverter<String> convenienceConverter() {
		return new StringConverter<String>() {
			@Override
			public String toString(final String committedString) {
				return committedString;
			}

			@Override
			public String fromString(final String stringFromEditing) {
				return formatter().apply(stringFromEditing);
			}
		};
	}

	private Function<String, String> formatter() {
		return s -> s.replace(".", ",");
	}

	private Function<String, String> unFormatter() {
		return s -> s.replace(",", ".");
	}

	@Override
	public String unformat(final String input) {
		return unFormatter().apply(input);
	}

	@Override
	public String format(final String input) {
		return formatter().apply(input);
	}

	@Override
	public Function<String, ValidationResult> validator() {
		return validator;
	}

	private static Function<String, ValidationResult> validator(IColumnSpecification columnSpecification) {
		// Definition: the pattern for string value is the same as the constructor of BigDecimal requires, but instead of the '.' a ',' needs to be used
		// Examples:
		// '23': valid
		// '23,1': valid
		// '23.1': invalid

		// Idea:
		// 1. convenience converter helping user while editing or in Cell.updateItem to create correct format if possible. E.g.: '23.1' -> '23,1'
		// 2. validation converter to convert formatted String to String which can be used as input for validation: '23,1' -> '23.1' (for BigDecimal constructor)
		// 3. (not part of validation) a formatter to render a real value (BigDecimal) into a formatted string is needed for the initial String values. It's behavior is the reverse of 2.
		//    TODO: think about where 3. belongs to

		return value -> {
			ValidationResult vr;
			if (value.contains(".")) { // to be supported by convenience converter
				vr = new ValidationResult(false, "'.' not allowed. use ',' instead.");
			}
			else {
//				String bigDecimalString = value.replace(",", "."); // done by validation converter
				String bigDecimalString = columnSpecification.unformat(value);

				// Validation (2.):
				try {
					new BigDecimal(bigDecimalString); // throws NumberFormatException if not valid
					vr = ValidationResult.VALID;
				} catch (NumberFormatException e) {
					vr = new ValidationResult(false, "wrong format for bigDecimalValue: " + value);
				}
			}
			return vr;
		};
	}
}
