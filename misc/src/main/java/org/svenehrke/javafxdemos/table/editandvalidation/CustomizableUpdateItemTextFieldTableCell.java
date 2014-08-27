package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

import java.util.function.BiConsumer;

public class CustomizableUpdateItemTextFieldTableCell<S, T> extends TextFieldTableCell<S, T> {

	private final BiConsumer<TextFieldTableCell<S, T>, T> consumer;

	public CustomizableUpdateItemTextFieldTableCell(final StringConverter<T> converter, final BiConsumer<TextFieldTableCell<S, T>, T> consumer) {

		super(converter);
		this.consumer = consumer;
	}

	@Override
	/** Called when the list view is initially loaded with data or when the editing process of a cell is finished. */
	public void updateItem(final T item, final boolean empty) {
		super.updateItem(item, empty);
		consumer.accept(this, item);
	}


}
