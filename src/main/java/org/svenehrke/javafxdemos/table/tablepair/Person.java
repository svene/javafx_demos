package org.svenehrke.javafxdemos.table.tablepair;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;

import java.util.Arrays;
import java.util.List;

public class Person {

	private final MySimpleObjectProperty<TableCellItem> name1;
	private final MySimpleObjectProperty<TableCellItem> name2;
	private final MySimpleObjectProperty<TableCellItem> name3;
	private final MySimpleObjectProperty<TableCellItem> name4;

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
		attributes().forEach(a -> a.addListener(listener));

		big.addListener((s,o,n) -> {
			System.out.printf("Person.big: %s -> %s%n", o, n);
			attributes().forEach(a -> a.fireValueChangedEvent());
		});
	}

	private MySimpleObjectProperty<TableCellItem> newProperty(int colIdx, final int rowIdx) {
		TableCellItem tci = new TableCellItem("name " + colIdx + " / " + rowIdx);
		return new MySimpleObjectProperty<>(tci);
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

	public List<MySimpleObjectProperty<TableCellItem>> attributes() {
		return Arrays.asList(name1, name2, name3, name4);
	}

	public static boolean _isBig(final String item) {
		return item.length() > 12;
	}

}
