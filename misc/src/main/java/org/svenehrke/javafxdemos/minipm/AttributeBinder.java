package org.svenehrke.javafxdemos.minipm;

import javafx.beans.property.BooleanProperty;

class AttributeBinder {

	public static void bindValidation(final Attribute valueAttribute, final Attribute validityAttribute) {
		valueAttribute.value().addListener((s, o, n) -> {
			String sn = (String) n;
			validityAttribute.value().set(sn.length() < 3);
		});
	}

	public static void bindImpulseListener(BooleanProperty booleanProperty, Runnable runnable) {
		booleanProperty.addListener((s,o,n) -> {
			if (!n) return;
			runnable.run();
			booleanProperty.setValue(false);
		});
	}
}
