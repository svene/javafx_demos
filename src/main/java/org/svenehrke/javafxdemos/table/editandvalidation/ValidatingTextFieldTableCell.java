package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import org.svenehrke.javafxdemos.common.Styles;

public class ValidatingTextFieldTableCell<S, T> extends TextFieldTableCell<S, T> {

	public ValidatingTextFieldTableCell(final StringConverter<T> converter) {
		super(converter);
	}

	@Override
	/** Called when the list view is initially loaded with data or when the editing process of a cell is finished. */
	public void updateItem(final T item, final boolean empty) {
		super.updateItem(item, empty);
		boolean isValid = getValidationResult(item);
		if (item != null) {
			int idx = getIndex();
			System.out.println("isvalid " + isValid);
			this.pseudoClassStateChanged(Styles.CSS_PC_INVALID, !isValid);
		}
		else {
			this.pseudoClassStateChanged(Styles.CSS_PC_INVALID, isValid);
		}
	}

	private boolean getValidationResult(final T item) {
		return getConverter().toString(item).length() > 3;
	}
}
