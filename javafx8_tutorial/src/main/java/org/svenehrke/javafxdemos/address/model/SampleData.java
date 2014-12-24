package org.svenehrke.javafxdemos.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SampleData {


	public static ObservableList<Person> getPeople() {
		ObservableList<Person> result = FXCollections.observableArrayList();
		result.add(new Person("Hans", "Muster"));
		result.add(new Person("Ruth", "Mueller"));
		result.add(new Person("Heinz", "Kurz"));
		result.add(new Person("Cornelia", "Meier"));
		result.add(new Person("Werner", "Meyer"));
		result.add(new Person("Lydia", "Kunz"));
		result.add(new Person("Anna", "Best"));
		result.add(new Person("Stefan", "Meier"));
		result.add(new Person("Martin", "Mueller"));
		return result;
	}

}
