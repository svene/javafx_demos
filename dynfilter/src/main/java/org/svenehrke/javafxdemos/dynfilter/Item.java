package org.svenehrke.javafxdemos.dynfilter;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;

public class Item {

	public static final String RED = "red";
	public static final String BLUE = "blue";

	private StringProperty name = new SimpleStringProperty();
	private StringProperty color = new SimpleStringProperty();

	public Item(String name, String color) {
		this.name.setValue(name);
		this.color.setValue(color);
	}

	public String getName() {
		return name.get();
	}

	public StringProperty nameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getColor() {
		return color.get();
	}

	public StringProperty colorProperty() {
		return color;
	}

	public void setColor(String color) {
		this.color.set(color);
	}

	static Callback<Item, Observable[]> colorExtractor() {
		return (Item item) -> new Observable[] {item.color};
	}
}
