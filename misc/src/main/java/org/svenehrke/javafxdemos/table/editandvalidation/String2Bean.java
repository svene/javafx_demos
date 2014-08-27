package org.svenehrke.javafxdemos.table.editandvalidation;

public class String2Bean {
	private ValidatedString string1 = new ValidatedString("");
	private String string2;

	public String2Bean(final String string1, final String string2) {
		this.string1.setText(string1);
		this.string2 = string2;
	}

	public ValidatedString string1Object() {
		return string1;
	}

	public String getString1() {
		return string1.getText();
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

