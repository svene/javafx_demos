package org.svenehrke.javafxdemos.table.editandvalidation;

import java.math.BigDecimal;
import java.util.function.Function;

import static org.svenehrke.javafxdemos.table.editandvalidation.ValidatedStrings.bindValidatorToValidatedString;

class PersonTableBeanBuilder {
	private final PersonTableSpecification tableSpecification;

	static PersonTableBeanBuilder newPersonTableBeanBuilder(PersonTableSpecification tableSpecification) {
		return new PersonTableBeanBuilder(tableSpecification);
	}

	PersonTableBeanBuilder(PersonTableSpecification tableSpecification) {
		this.tableSpecification = tableSpecification;
	}

	/** Will be needed to add new Persons from the GUI */
	PersonTableBean fromPresentation(long id, String firstName, final String lastName, String value) {
		PersonTableBean result = new PersonTableBean(id, "", "", "");

		initValidatedString(firstName, tableSpecification.firstNameSpec().validator(), result.firstName());
		initValidatedString(value, tableSpecification.bdValueSpec().validator(), result.bigDecimalValue());

		result.setLastName(lastName);
		return result;
	}


	PersonTableBean fromPersistence(long id, String firstName, final String lastName, BigDecimal value) {
		PersonTableBean result = new PersonTableBean(id, "", "", "");

		initValidatedString(firstName, tableSpecification.firstNameSpec().validator(), result.firstName());

		String bigDecimalAsString = tableSpecification.bdValueSpec().format(value.toString());
		initValidatedString(bigDecimalAsString, tableSpecification.bdValueSpec().validator(), result.bigDecimalValue());

		result.setLastName(lastName);
		return result;
	}

	private void initValidatedString(final String value, Function<String, ValidationResult> validator, ValidatedString validatedString) {
		bindValidatorToValidatedString(validatedString, validator);
		validatedString.setText(value);
	}
}
