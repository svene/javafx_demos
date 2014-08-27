package org.svenehrke.javafxdemos.table.editandvalidation;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

class PersonTableBeanBuilder {
	private final List<IColumnSpecification> columnSpecifications;

	static PersonTableBeanBuilder newPersonTableBeanBuilder(final List<IColumnSpecification> columnSpecifications) {
		return new PersonTableBeanBuilder(columnSpecifications);
	}

	PersonTableBeanBuilder(final List<IColumnSpecification> columnSpecifications) {
		this.columnSpecifications = columnSpecifications;
	}

	/** Will be needed to add new Persons from the GUI */
	PersonTableBean fromPresentation(long id, String firstName, final String lastName, String value) {
		PersonTableBean result = new PersonTableBean(id, "", "", "");

		initValidatedString(firstName, PersonTableSpecification2.firstNameSpec(columnSpecifications).validator(), result.firstName());
		initValidatedString(value, PersonTableSpecification2.bdValueSpec(columnSpecifications).validator(), result.bigDecimalValue());

		result.setLastName(lastName);
		return result;
	}


	PersonTableBean fromPersistence(long id, String firstName, final String lastName, BigDecimal value) {
		PersonTableBean result = new PersonTableBean(id, "", "", "");

		initValidatedString(firstName, PersonTableSpecification2.firstNameSpec(columnSpecifications).validator(), result.firstName());

		String bigDecimalAsString = PersonTableSpecification2.bdValueSpec(columnSpecifications).format(value.toString());
		initValidatedString(bigDecimalAsString, PersonTableSpecification2.bdValueSpec(columnSpecifications).validator(), result.bigDecimalValue());

		result.setLastName(lastName);
		return result;
	}

	private void initValidatedString(final String value, Function<String, ValidationResult> validator, ValidatedString validatedString) {
		ValidatedStrings.bindValidatorToValidatedString(validatedString, validator);
		validatedString.setText(value);
	}
}
