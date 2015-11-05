package org.svenehrke.javafxdemos.greetfxml;

import javafx.fxml.FXMLLoader;

import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ApplicationConfig {

	Supplier<URL> fxmlUrlSupplier() {
		return () -> ApplicationConfig.class.getResource("/greet.fxml");
	}

	Supplier<ResourceBundle> resources() {
		final String bundleName = "bundles.app";
		try {
			return () -> ResourceBundle.getBundle(bundleName, Locale.getDefault());
		} catch (MissingResourceException e) {
			Locale.setDefault(Locale.ENGLISH);
			return () -> ResourceBundle.getBundle(bundleName, Locale.getDefault());
		}
	}

	Consumer<FXMLLoader> binder() {
		return loader -> new GUIBinder(loader.getController(), new PresentationState()).bindAndInitialize();
	}
}
