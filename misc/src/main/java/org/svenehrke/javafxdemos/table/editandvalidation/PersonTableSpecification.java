package org.svenehrke.javafxdemos.table.editandvalidation;

import java.util.List;

public class PersonTableSpecification extends DefaultTableSpecification {
	public PersonTableSpecification(final List<IColumnSpecification> columnSpecifications) {
		super(columnSpecifications);
	}

	IColumnSpecification bdValueSpec() {
		return getColumnSpecifications().get(2);
	}

	IColumnSpecification firstNameSpec() {
		return getColumnSpecifications().get(0);
	}
}
