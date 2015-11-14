package org.svenehrke.javafxdemos.greetfxml;

import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class JavaFxApplications {

	private JavaFxApplications() {
	}

	public static URL fxmlUrl(String fxmlUrl) {
		return JavaFxApplications.class.getResource(fxmlUrl);
	}

	public static ResourceBundle resources(String resourceBundleName) {
		try {
			return ResourceBundle.getBundle(resourceBundleName, Locale.getDefault());
		} catch (MissingResourceException e) {
			Locale.setDefault(Locale.ENGLISH);
			return ResourceBundle.getBundle(resourceBundleName, Locale.getDefault());
		}
	}

}
