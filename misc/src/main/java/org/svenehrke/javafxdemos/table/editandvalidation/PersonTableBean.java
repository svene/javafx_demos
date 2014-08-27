package org.svenehrke.javafxdemos.table.editandvalidation;

public class PersonTableBean {
	private long id;

	private ValidatedString firstName = new ValidatedString("");
	private String lastName;
	private ValidatedString bigDecimalValue = new ValidatedString("");

	public PersonTableBean(long id, final String firstName, final String lastName, final String bigDecimalValue) {
		this.id = id;
		this.firstName.setText(firstName);
		this.lastName = lastName;
		this.bigDecimalValue.setText(bigDecimalValue);
	}

	public long getId() {
		return id;
	}

// --- fields to be validated ---

	public ValidatedString firstName() {
		return firstName;
	}

	public ValidatedString bigDecimalValue() {
		return bigDecimalValue;
	}


	// --- fields not to be validated ---

	public String getLastName() {
		return lastName;
	}
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}


	@Override
	public String toString() {
		return "String2Bean{" +
			"firstName=" + firstName.getText() +
			", lastName='" + lastName + '\'' +
			", bigDecimalValue=" + bigDecimalValue.getText() +
			'}';
	}
}

