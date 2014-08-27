package org.svenehrke.javafxdemos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;

public class FilteredListTest {

	private ObservableList<String> list;
	private FilteredList<String> filteredList;

	@Before
	public void setUp() {
		list = FXCollections.observableArrayList();
		list.addAll("a", "c", "d", "c");
		Predicate<String> predicate = (String e) -> !e.equals("c");
		filteredList = new FilteredList<>(list, predicate);
	}

	@Test
	public void testLiveMode() {
		assertEquals(Arrays.asList("a", "d"), filteredList);
	}

	@Test
	public void testLiveMode_Add() {
		list.clear();
		assertEquals(Collections.emptyList(), filteredList);
		list.addAll("a", "c", "d", "c");
		assertEquals(Arrays.asList("a", "d"), filteredList);
		list.add("c");
		list.add(1, "b");
		assertEquals(Arrays.asList("a", "b", "d"), filteredList);

		list.remove("a");
		assertEquals(Arrays.asList("b", "d"), filteredList);
	}


}
