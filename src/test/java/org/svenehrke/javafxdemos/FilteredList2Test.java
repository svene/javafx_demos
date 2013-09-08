package org.svenehrke.javafxdemos;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class FilteredList2Test {

	static class Person {
		String name;
		SimpleBooleanProperty delete = new SimpleBooleanProperty(false);

		Person(final String name) {
			this.name = name;
		}

		boolean getDelete() {
			return delete.get();
		}

		SimpleBooleanProperty deleteProperty() {
			return delete;
		}

		void setDelete(final boolean delete) {
			this.delete.set(delete);
		}

		@Override
		public String toString() {
			return name;
		}
	}

	private ObservableList<Person> list;
	private FilteredList<Person> filteredList;

	@Before
	public void setUp() {
		list = FXCollections.observableArrayList();
		list.addAll(newPerson("a"), newPerson("b"), newPerson("c"), newPerson("d"));
		Predicate<Person> predicate = (Person e) -> {
			System.out.println("in predicate");
			return !e.getDelete();
		};
		filteredList = new FilteredList<>(list, predicate);
	}

	@Test
	public void init() {
		assertEquals("a-b-c-d", names()); // actually "b-d-d" would be expected, but the update change is not detected
	}

	@Test
	public void change() {
		list.get(0).setDelete(true);
		assertEquals("a-b-c-d", names());
	}

	private String names() {
		return filteredList.stream().map(p -> p.name).collect(Collectors.joining("-")).toString();
	}



	private Person newPerson(String name) {
		return new Person(name);
	}


}
