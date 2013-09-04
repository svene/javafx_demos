package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ValidatedString {

	private StringProperty text = new SimpleStringProperty();
	private ValidationResult validationResult = new ValidationResult(true, "");

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

}
