package org.svenehrke.javafxdemos.minipm;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.svenehrke.javafxdemos.common.Styles;

class MainGUIScope {

	private final MainAttributeScope mainAttributeScope;
	private final Label label;
	private final TextField textField1;
	private final TextField textField2;

	public MainGUIScope(final MainAttributeScope mainAttributeScope, final Label label, final TextField textField1, final TextField textField2) {
		this.mainAttributeScope = mainAttributeScope;
		this.label = label;
		this.textField1 = textField1;
		this.textField2 = textField2;
	}

	public void bind() {
		mainAttributeScope.firstNameAttribute().value().bindBidirectional(label.textProperty());
		// First TextField: change attribute with each key pressed
		mainAttributeScope.firstNameAttribute().value().bindBidirectional(textField1.textProperty());
		mainAttributeScope.firstNameValidAttribute().value().addListener((s, o, n) -> textField1.pseudoClassStateChanged(Styles.CSS_PC_INVALID, !(Boolean) n));
		// Second TextField: only change attribute, when ENTER is pressed
		textField2.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
			if (KeyCode.ENTER == event.getCode()) {
				mainAttributeScope.firstNameAttribute().value().setValue(textField2.getText());
			}
		});
		mainAttributeScope.firstNameAttribute().value().addListener((s, o, n) -> textField2.setText((String) n));
	}
}
