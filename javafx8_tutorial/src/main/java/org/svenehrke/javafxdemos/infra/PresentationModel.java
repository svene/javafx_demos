package org.svenehrke.javafxdemos.infra;

import org.svenehrke.javafxdemos.address.model.Person;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PresentationModel {
	private String id;
	private Map<String, Attribute> attributesByPropertyname = new HashMap<>();

	public PresentationModel(String id, Attribute...attributes) {
		this.id = id;
		for (Attribute attribute : attributes) {
			attributesByPropertyname.put(attribute.getPropertyName(), attribute);
		}
	}

	public String getId() {
		return id;
	}

	public Attribute getAttribute(String propertyName) {
		return attributesByPropertyname.get(propertyName);
	}

	public Collection<Attribute> allAttributes() {
		return attributesByPropertyname.values();
	}

	public void populateFromPresentationModel(PresentationModel other, boolean usingQualifier) {
		this.id = other.id;
		attributesByPropertyname.values().forEach(att -> att.populateFromAttribute(other.getAttribute(att.getPropertyName()), usingQualifier));
	}
}
