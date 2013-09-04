package org.svenehrke.javafxdemos.table.editandvalidation;

public class String2Bean {
	private ValidatingString string1;
	private String string2;

	public String2Bean(final ValidatingString string1, final String string2) {
		this.string1 = string1;
		this.string2 = string2;
	}

	public String getString1() {
		return string1.getText();
	}
	public ValidatedString getString1Object() {
		return string1;
	}

	public String getString2() {
		return string2;
	}

	public void setString1(final String string1) {
		this.string1.setText(string1);
	}

	public void setString2(final String string2) {
		this.string2 = string2;
	}

	@Override
	public String toString() {
		return "String2Bean{" +
			"string1=" + string1.getText() +
			", string2='" + string2 + '\'' +
			'}';
	}
}

