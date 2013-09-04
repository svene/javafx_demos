package org.svenehrke.javafxdemos.table.editandvalidation;

public class ValidationResult {
	private final boolean isValid;
	private final String errorMessage;

	public ValidationResult(final boolean valid, final String errorMessage) {
		isValid = valid;
		this.errorMessage = errorMessage;
	}

	public boolean isValid() {
		return isValid;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
