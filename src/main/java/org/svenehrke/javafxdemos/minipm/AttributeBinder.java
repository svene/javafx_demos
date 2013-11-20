package org.svenehrke.javafxdemos.minipm;

class AttributeBinder {

	public static void bindValidation(final Attribute valueAttribute, final Attribute validityAttribute) {
		valueAttribute.value().addListener((s, o, n) -> {
			String sn = (String) n;
			validityAttribute.value().set(sn.length() < 3);
		});
	}
}
