package org.svenehrke.javafxdemos.minipm;

class MainAttributeScope {

	public static final String FIRST_NAME = "firstName";
	private final AttributeStore attributeStore;

	public MainAttributeScope(final AttributeStore attributeStore) {
		this.attributeStore = attributeStore;
	}

	public void initilizePMs() {
		Attribute fna = attributeStore.newValueAttribute(FIRST_NAME, "");
		Attribute fnva = attributeStore.newValidAttribute(FIRST_NAME);

		AttributeBinder.bindValidation(fna, fnva);
	}


	Attribute firstNameAttribute() {
		return attributeStore.findValueAttribute(FIRST_NAME);
	}

	Attribute firstNameValidAttribute() {
		return attributeStore.findAttribute(FIRST_NAME, Tag.VALID);
	}
}
