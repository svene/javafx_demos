package org.svenehrke.javafxdemos.minipm;

public class Tag {
	private final String value;

	public Tag(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static final Tag VALUE = new Tag("value");
	public static final Tag REGEX = new Tag("regex");
	public static final Tag MANDATORY = new Tag("mandatory");
	public static final Tag VISIBLE = new Tag("visible");
	public static final Tag ENABLED = new Tag("endabled");
	public static final Tag VALID = new Tag("valid");
}
