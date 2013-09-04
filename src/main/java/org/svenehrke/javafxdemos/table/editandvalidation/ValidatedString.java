package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.function.Function;

public class ValidatedString {

	private StringProperty text = new SimpleStringProperty();
	private ValidationResult validationResult;

	public ValidatedString(final String text) {
		setText(text);
	}

	public String getText() {
		return text.get();
	}

	public StringProperty textProperty() {
		return text;
	}

	public void setText(final String text) {
		this.text.set(text);
	}

	public ValidationResult getValidationResult() {
		return validationResult;
	}

	public void setValidationResult(final ValidationResult validationResult) {
		this.validationResult = validationResult;
	}

	public boolean isValid() {
		return validationResult != null && validationResult.isValid();
	}


	public void addValidatingListener(Function<String, ValidationResult> validator) {
		textProperty().addListener((observable, oldValue, newValue) -> {
			setValidationResult(validator.apply(newValue));
		});
	}
}
