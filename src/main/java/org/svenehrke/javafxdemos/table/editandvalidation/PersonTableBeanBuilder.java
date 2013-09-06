package org.svenehrke.javafxdemos.table.editandvalidation;

import java.util.function.Function;

import static org.svenehrke.javafxdemos.table.editandvalidation.ValidatedStrings.bindValidatorToValidatedString;

class PersonTableBeanBuilder {
	private final ITableSpecification tableSpecification;

	static PersonTableBeanBuilder newPersonTableBeanBuilder(ITableSpecification tableSpecification) {
		return new PersonTableBeanBuilder(tableSpecification);
	}

	PersonTableBeanBuilder(ITableSpecification tableSpecification) {
		this.tableSpecification = tableSpecification;
	}

	PersonTableBean newPersonTableBean(String firstName, final String lastName, String value) {
		PersonTableBean result = new PersonTableBean("", "", "");

		initValidatedString(firstName, tableSpecification.getColumnSpecifications().get(0).validator(), result.firstName());
		initValidatedString(value, tableSpecification.getColumnSpecifications().get(2).validator(), result.bigDecimalValue());

		result.setLastName(lastName);
		return result;
	}

	private void initValidatedString(final String value, Function<String, ValidationResult> validator, ValidatedString validatedString) {
		bindValidatorToValidatedString(validatedString, validator);
		validatedString.setText(value);
	}
}
