package org.svenehrke.javafxdemos.greetfxml;

import javafx.beans.InvalidationListener;

public class ActionHandlers {

	public static InvalidationListener greetHandler(PresentationState ps) {
		return observable -> ps.greeting.setValue("Hello " + ps.name.getValue());
	}
}
