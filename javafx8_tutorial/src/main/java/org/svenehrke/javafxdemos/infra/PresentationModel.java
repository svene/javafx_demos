package org.svenehrke.javafxdemos.infra;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.*;

public class PresentationModel {
	private String id;
	private Map<String, Attribute> attributesByPropertyname = new HashMap<>();
//	private Set<String> tags = new HashSet<>();
	public StringProperty tag = new SimpleStringProperty();

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

	public void addTag(String tag) {
		this.tag.setValue(tag);
	}

	public boolean hasTag(String tag) {
		return tag.equals(this.tag.getValue());
	}
}
