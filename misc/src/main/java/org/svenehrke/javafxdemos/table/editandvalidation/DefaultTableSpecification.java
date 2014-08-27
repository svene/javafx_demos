package org.svenehrke.javafxdemos.table.editandvalidation;

import java.util.List;

public class DefaultTableSpecification implements ITableSpecification {
	private final List<IColumnSpecification> columnSpecifications;

	public DefaultTableSpecification(final List<IColumnSpecification> columnSpecifications) {
		this.columnSpecifications = columnSpecifications;
	}

	@Override
	public List<IColumnSpecification> getColumnSpecifications() {
		return columnSpecifications;
	}
}
