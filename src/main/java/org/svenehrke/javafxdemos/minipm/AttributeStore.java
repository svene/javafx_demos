package org.svenehrke.javafxdemos.minipm;

import java.util.HashMap;
import java.util.Map;

public class AttributeStore {

	private long counter = 0;

	private Map<Long, Attribute> attributesByIdStore = new HashMap<>();
	public void put(Attribute attribute) {
		counter++;
		attributesByIdStore.put(counter, attribute);
	}
	public Attribute get(Long key) {
		return attributesByIdStore.get(key);
	}

	public Attribute findValueAttribute(final String propertyName) {
		return attributesByIdStore.values().stream()
			.filter(attr -> propertyName.equals(attr.getPropertyName()) && Tag.VALUE == attr.getTag())
			.findFirst().orElseGet(null);
	}
	public Attribute findAttribute(final String propertyName, Tag tag) {
		return attributesByIdStore.values().stream()
			.filter(attr -> propertyName.equals(attr.getPropertyName()) && tag == attr.getTag())
			.findFirst().orElseGet(null);
	}
}
