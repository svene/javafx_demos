package org.svenehrke.javafxdemos.dynfilter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import org.junit.Test;

public class ItemsTest {

	@Test
	public void test1() throws Exception {

		ObservableList<Item> allItems = FXCollections.observableArrayList(Item.colorExtractor());
		ObservableList<Item> blueItems;

		Item firstItem= new Item("1", Item.RED);
		Item secondItem= new Item("2", Item.BLUE);

		blueItems = new FilteredList<>(allItems, item -> Item.BLUE.equals(item.getColor()));
		allItems.addAll(firstItem, secondItem );

		secondItem.colorProperty().setValue(Item.RED);
		secondItem.colorProperty().setValue(Item.BLUE);
		secondItem.colorProperty().setValue(Item.RED); // ArrayIndexOutOfBoundsException is thrown and caught internally leaving the lists in a corrupt state
	}
}