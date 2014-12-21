package org.svenehrke.javafxdemos.infra;

import javafx.beans.property.BooleanProperty;

public class ImpulseListeners {

	public static void bindImpulseListener(BooleanProperty booleanProperty, Runnable runnable) {
		booleanProperty.addListener((s, o, n) -> {
			if (!n) return;
			runnable.run();
			booleanProperty.setValue(false);
		});
	}

}
