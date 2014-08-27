package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.svenehrke.javafxdemos.table.editandvalidation.persistence.Person;

import java.util.stream.Stream;

class PersistenceAndGUIMapper {

	/**
	 * Map Persons from Repository to GUI-Persons (PersonTableBean objects)
	 **/
	static ObservableList<PersonTableBean> people(PersonTableBeanBuilder builder, Stream<Person> personStream) {
		ObservableList<PersonTableBean> result = FXCollections.observableArrayList();
		personStream
			.map(person -> builder.fromPersistence(person.getId(), person.getFirstName(), person.getLastName(), person.getBdValue()))
			.forEach(result::add);
		return result;
	}



}
