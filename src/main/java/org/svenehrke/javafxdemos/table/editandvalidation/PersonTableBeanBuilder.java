package org.svenehrke.javafxdemos.table.editandvalidation;

import java.util.function.Function;

import static org.svenehrke.javafxdemos.table.editandvalidation.ValidatedStrings.bindValidatorToValidatedString;

class PersonTableBeanBuilder {
	final Function<String, ValidationResult> firstnameValidator, bigDecimalValueValidator;

	PersonTableBeanBuilder(final Function<String, ValidationResult> firstnameValidator, final Function<String, ValidationResult> bigDecimalValueValidator) {
		this.firstnameValidator = firstnameValidator;
		this.bigDecimalValueValidator = bigDecimalValueValidator;
	}

	PersonTableBean newPersonTableBean(String firstName, final String lastName, String value) {
		PersonTableBean result = new PersonTableBean("", "", "");

		bindValidatorToValidatedString(result.firstName(), firstnameValidator);
		result.firstName().setText(firstName);

		bindValidatorToValidatedString(result.bigDecimalValue(), bigDecimalValueValidator);
		result.bigDecimalValue().setText(value);

		result.setLastName(lastName);
		return result;
	}
}
