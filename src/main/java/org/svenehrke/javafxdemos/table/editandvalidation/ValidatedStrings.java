package org.svenehrke.javafxdemos.table.editandvalidation;

import java.util.function.Function;

public class ValidatedStrings {

	public static void bindValidatorToValidatedString(final ValidatedString validatedString, final Function<String, ValidationResult> validator) {
		validatedString.textProperty().addListener((observable, oldValue, newValue) -> {
			validatedString.setValidationResult(validator.apply(newValue));
		});

	}
}
