package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import org.svenehrke.javafxdemos.common.Styles;

import java.util.function.Function;

public class ValidatingTextFieldTableCell<S, T> extends TextFieldTableCell<S, T> {

	private final Function<T, ValidationResult> validator;

	public ValidatingTextFieldTableCell(final StringConverter<T> converter, final Function<T, ValidationResult> validator) {
		super(converter);
		this.validator = validator;
	}

	@Override
	/** Called when the list view is initially loaded with data or when the editing process of a cell is finished. */
	public void updateItem(final T item, final boolean empty) {
		super.updateItem(item, empty);
		boolean isValid = validator.apply(item).isValid();
		if (item != null) {
			int idx = getIndex();
			this.pseudoClassStateChanged(Styles.CSS_PC_INVALID, !isValid);
		}
		else {
			this.pseudoClassStateChanged(Styles.CSS_PC_INVALID, isValid);
		}
	}


}
