package org.svenehrke.fxmlguis.infra;

import javafx.beans.InvalidationListener;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class JavaFxWidgetBindings {

	public static void bindTextField(TextField textField, StringProperty stringProperty) {
		textField.textProperty().bindBidirectional(stringProperty);

	}
	public static void bindLabel(Label label, StringProperty stringProperty) {
		label.textProperty().bind(stringProperty);

	}
	public static void bindButton(Button button, InvalidationListener invalidationListener) {
		button.setOnAction(event -> triggerAction(invalidationListener));
	}

	public static void triggerAction(InvalidationListener invalidationListener) {
		invalidationListener.invalidated(null);
	}
}
