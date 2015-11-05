package org.svenehrke.javafxdemos.greetfxml;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PresentationState {

	public StringProperty name = new SimpleStringProperty();
	public StringProperty greeting = new SimpleStringProperty();
	public InvalidationListener greetTrigger;

	public void setupBinding() {
		greetTrigger = observable -> greeting.setValue("Hello " + name.getValue());
	}

	public void initData() {
		name.setValue("Duke");
	}
}
