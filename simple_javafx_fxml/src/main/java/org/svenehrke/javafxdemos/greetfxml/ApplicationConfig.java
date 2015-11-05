package org.svenehrke.javafxdemos.greetfxml;

import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ApplicationConfig {

	private final String fxmlUrl;
	private final String resourceBundleName;

	public ApplicationConfig(String fxmlUrl, String resourceBundleName) {
		this.fxmlUrl = fxmlUrl;
		this.resourceBundleName = resourceBundleName;
	}

	URL fxmlUrl() {
		return ApplicationConfig.class.getResource(fxmlUrl);
	}

	ResourceBundle resources() {
		final String bundleName = resourceBundleName;
		try {
			return ResourceBundle.getBundle(bundleName, Locale.getDefault());
		} catch (MissingResourceException e) {
			Locale.setDefault(Locale.ENGLISH);
			return ResourceBundle.getBundle(bundleName, Locale.getDefault());
		}
	}

}
