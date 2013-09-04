package org.svenehrke.javafxdemos.table.editandvalidation;

public class ValidatedString {
	private String text;
	private ValidationResult validationResult;

	public ValidatedString(final String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public ValidationResult getValidationResult() {
		return validationResult;
	}

	public void setText(final String text) {
		this.text = text;
	}

	public void setValidationResult(final ValidationResult validationResult) {
		this.validationResult = validationResult;
	}

	public boolean isValid() {
		return validationResult != null && validationResult.isValid();
	}
}
