package org.svenehrke.javafxdemos.table.editandvalidation;

public class String2Bean {
	private String string1;
	private String string2;

	public String2Bean(final String string1, final String string2) {
		this.string1 = string1;
		this.string2 = string2;
	}

	public String getString1() {
		return string1;
	}

	public String getString2() {
		return string2;
	}

	public void setString1(final String string1) {
		this.string1 = string1;
	}

	public void setString2(final String string2) {
		this.string2 = string2;
	}
}

