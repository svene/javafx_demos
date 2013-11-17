package org.svenehrke.javafxdemos.minipm;

import org.svenehrke.javafxdemos.table.editandvalidation.persistence.Person;
import org.svenehrke.javafxdemos.table.editandvalidation.persistence.PersonRepository;

class MainScope {

	public static final String FIRST_NAME = "firstName";
	private final AttributeStore attributeStore;

	public MainScope(final AttributeStore attributeStore) {
		this.attributeStore = attributeStore;
	}

	public void initializePresentationModels() {
		attributeStore.put(new Attribute(FIRST_NAME, ""));
		attributeStore.put(new Attribute(FIRST_NAME, Boolean.TRUE, Tag.VALID));

		getFirstNameAttribute().value().addListener((s, o, n) -> {
			String sn = (String) n;
			getFirstNameValidAttribute().value().set(sn.length() < 3);
		});
	}

	public Attribute getFirstNameAttribute() {
		return attributeStore.findValueAttribute(FIRST_NAME);
	}

	public Attribute getFirstNameValidAttribute() {
		return attributeStore.findAttribute(FIRST_NAME, Tag.VALID);
	}

	public void load() {
		Person person = PersonRepository.getInstance().people().stream().findFirst().get();
		getFirstNameAttribute().value().setValue(person.getFirstName());
	}

}
