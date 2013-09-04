package org.svenehrke.javafxdemos.table.editandvalidation;

import java.util.function.Function;

/**
 * Like {@link ValidatedString} but will actively {@link ValidatedString#validationResult} whenever {@link ValidatedString#text} changes
 */
public class ValidatingString extends ValidatedString {
	private final Function<String, ValidationResult> validator;

	public ValidatingString(final String text, Function<String, ValidationResult> validator) {
		super(text);
		this.validator = validator;
		setText(text);
	}

	@Override
	public void setText(final String text) {
		super.setText(text);
		setValidationResult(validator.apply(text));
	}
}
