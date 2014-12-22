package org.svenehrke.javafxdemos.infra;

import javafx.beans.property.BooleanProperty;

public class ImpulseListeners {

	public static void bindImpulseResetter(BooleanProperty booleanProperty) {
		addImpulseListener(booleanProperty, () -> booleanProperty.setValue(false));
	}

	public static void addImpulseListener(BooleanProperty booleanProperty, Runnable runnable) {
		booleanProperty.addListener((s,o,n) -> {
			if (n) {
				runnable.run();
			}
		});
	}

}
