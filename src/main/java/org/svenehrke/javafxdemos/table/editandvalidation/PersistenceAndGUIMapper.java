package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.svenehrke.javafxdemos.table.editandvalidation.persistence.PersonRepository;

class PersistenceAndGUIMapper {

	/**
	 * Map Persons from Repository to GUI-Persons (PersonTableBean objects)
	 **/
	static ObservableList<PersonTableBean> people(PersonTableBeanBuilder builder) {
		ObservableList<PersonTableBean> result = FXCollections.observableArrayList();
		PersonRepository.getInstance().people()
			.stream()
			.map(person -> builder.fromPersistence(0, person.getFirstName(), person.getLastName(), person.getBdValue()))
			.forEach(result::add);
		return result;
	}



}
