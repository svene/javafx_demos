package org.svenehrke.javafxdemos.common;

import javafx.css.PseudoClass;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Styles {

	public static final PseudoClass CSS_PC_INVALID = PseudoClass.getPseudoClass("javafxdemos-invalid");

	public static void addStyleSheetTo(Parent parent) {
		parent.getStylesheets().add(StylesheetURI());
	}

	public static void addStyleSheetTo(Scene scene) {
		scene.getStylesheets().add(StylesheetURI());
	}

	private static String StylesheetURI() {
		return Styles.class.getResource("/org/svenehrke/javafxdemos/javafxdemos.css").toExternalForm();
	}

}
