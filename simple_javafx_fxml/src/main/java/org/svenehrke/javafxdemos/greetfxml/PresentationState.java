package org.svenehrke.javafxdemos.greetfxml;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PresentationState {

	public final StringProperty name = new SimpleStringProperty();
	public final StringProperty greeting = new SimpleStringProperty();

	public void initBinding() {
	}

	public void initData() {
		name.setValue("Duke");
	}
}
