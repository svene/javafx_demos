package org.svenehrke.javafxdemos.treetableview;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MyFile {


	private StringProperty name;
	private StringProperty lastModified;

	public MyFile(final String name, final String lastModified) {
		nameProperty().setValue(name);
		lastModifiedProperty().setValue(lastModified);
	}

	public StringProperty nameProperty() {
		if (name == null) name = new SimpleStringProperty(this, "name");
		return name;
	}

	public StringProperty lastModifiedProperty() {
		if (lastModified == null) lastModified = new SimpleStringProperty(this, "lastModified");
		return lastModified;
	}
}