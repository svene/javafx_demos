package org.svenehrke.javafxdemos.infra;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.*;

import static java.util.stream.Collectors.joining;

public class PresentationModel {
	private String id;
	private Map<String, Attribute> attributesByPropertyname = new HashMap<>();
	public StringProperty type = new SimpleStringProperty();

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
		attributesByPropertyname.values().forEach(att -> att.populateFromAttribute(other.getAttribute(att.getPropertyName()), usingQualifier));
	}

	public void setType(String tag) {
		this.type.setValue(tag);
	}

	public String getType() {
		return type.get();
	}

	@Override
	public String toString() {
		return "PresentationModel{" +
			"id='" + id.substring(0, 5) + '\'' +
			", attributes=" + attributesByPropertyname.entrySet().stream().map(me -> me.getKey() + ":" + me.getValue()).collect(joining(",")) +
			", type=" + type +
			'}';
	}
}
