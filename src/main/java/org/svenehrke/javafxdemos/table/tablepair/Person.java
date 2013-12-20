package org.svenehrke.javafxdemos.table.tablepair;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;

import java.util.Arrays;
import java.util.List;

public class Person {

	private final ObjectProperty<TableCellItem> name1;
	private final ObjectProperty<TableCellItem> name2;
	private final ObjectProperty<TableCellItem> name3;
	private final ObjectProperty<TableCellItem> name4;

	private final BooleanProperty big;

	public Person(final int rowIdx) {
		this.name1 = newProperty(1, rowIdx);
		this.name2 = newProperty(2, rowIdx);
		this.name3 = newProperty(3, rowIdx);
		this.name4 = newProperty(4, rowIdx);

		big = new SimpleBooleanProperty();

		ChangeListener<TableCellItem> listener = (s,o,n) -> {
			big.setValue(_isBig(name1.getValue().getValue()) || _isBig(name2.getValue().getValue()) || _isBig(name3.getValue().getValue()) || _isBig(name4.getValue().getValue()) );
		};
		name1.addListener(listener);
		name2.addListener(listener);
		name3.addListener(listener);
		name4.addListener(listener);

		big.addListener((s,o,n) -> {
			System.out.printf("Person.big: %s -> %s%n", o, n);
			name1.setValue(new TableCellItem(name1.getValue().getValue(), false));
			name2.setValue(new TableCellItem(name2.getValue().getValue(), false));
			name3.setValue(new TableCellItem(name3.getValue().getValue(), false));
			name4.setValue(new TableCellItem(name4.getValue().getValue(), false));
		});
	}

	private ObjectProperty<TableCellItem> newProperty(int colIdx, final int rowIdx) {
		TableCellItem tci = new TableCellItem("name " + colIdx + " / " + rowIdx, false);
		return new SimpleObjectProperty<>(tci);
	}

	public ObjectProperty<TableCellItem> name1Property() {
		return name1;
	}

	public ObjectProperty<TableCellItem> name2Property() {
		return name2;
	}

	public ObjectProperty<TableCellItem> name3Property() {
		return name3;
	}

	public ObjectProperty<TableCellItem> name4Property() {
		return name4;
	}

	public boolean getBig() {
		return big.get();
	}

	public BooleanProperty bigProperty() {
		return big;
	}

	public List<ObjectProperty<TableCellItem>> attributes() {
		return Arrays.asList(name1, name2, name3, name4);
	}

	public static boolean _isBig(final String item) {
		return item.length() > 12;
	}

}
