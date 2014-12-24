package org.svenehrke.javafxdemos.infra;

import javafx.fxml.FXMLLoader;
import org.svenehrke.javafxdemos.address.Main;

import java.io.IOException;
import java.net.URL;

public class FXMLLoader2 {
	public static FXMLLoader loadFXML(String fxmlPath) {
		URL resource = Main.class.getResource(fxmlPath);
		final FXMLLoader loader = new FXMLLoader(resource, null);
		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return loader;

	}
}
