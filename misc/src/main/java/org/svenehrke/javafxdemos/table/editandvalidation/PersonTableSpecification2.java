package org.svenehrke.javafxdemos.table.editandvalidation;

import java.util.List;

public class PersonTableSpecification2 {

	public static IColumnSpecification bdValueSpec(final List<IColumnSpecification> columnSpecifications) {
		return columnSpecifications.get(2);
	}

	public static IColumnSpecification firstNameSpec(final List<IColumnSpecification> columnSpecifications) {
		return columnSpecifications.get(0);
	}
}
