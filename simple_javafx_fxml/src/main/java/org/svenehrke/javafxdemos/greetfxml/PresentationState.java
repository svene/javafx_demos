package org.svenehrke.javafxdemos.greetfxml;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PresentationState {

	public StringProperty name = new SimpleStringProperty();
	public StringProperty greeting = new SimpleStringProperty();

	public void setupBinding() {
	}

	public void initData() {
		name.setValue("Duke");
	}
}
