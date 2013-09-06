package org.svenehrke.javafxdemos.table.editandvalidation.persistence;

import java.math.BigDecimal;

public class Person {
	private long id;
	private String firstName;
	private String lastName;
	private BigDecimal bdValue;

	public Person(final long id, final String firstName, final String lastName, final BigDecimal bdValue) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bdValue = bdValue;
	}

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public BigDecimal getBdValue() {
		return bdValue;
	}

	public void setBdValue(final BigDecimal bdValue) {
		this.bdValue = bdValue;
	}
}
