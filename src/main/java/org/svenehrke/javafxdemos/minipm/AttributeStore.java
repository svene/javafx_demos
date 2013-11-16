package org.svenehrke.javafxdemos.minipm;

import java.util.HashMap;
import java.util.Map;

public class AttributeStore {

	private Map<Long, Attribute> attributesByIdStore = new HashMap<>();
	public void put(Long key, Attribute attribute) {
		attributesByIdStore.put(key, attribute);
	}
	public Attribute get(Long key) {
		return attributesByIdStore.get(key);
	}
}
