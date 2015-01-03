package org.svenehrke.javafxdemos.dynfilter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public class Context {

	ObservableList<Item> allItems = FXCollections.observableArrayList(Item.colorExtractor());
	ObservableList<Item> blueItems;

	public Context() {

		blueItems = new FilteredList<>(allItems, item -> Item.BLUE.equals(item.getColor()));
		allItems.addAll(new Item("1", Item.RED), new Item("2", Item.BLUE) );
	}
}
