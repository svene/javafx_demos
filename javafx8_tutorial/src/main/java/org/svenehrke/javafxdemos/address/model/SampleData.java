package org.svenehrke.javafxdemos.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.svenehrke.javafxdemos.infra.ModelStore;

public class SampleData {


	public static ObservableList<Person> getPeople(ModelStore modelStore) {
		ObservableList<Person> result = FXCollections.observableArrayList();
		result.add(modelStore.newPerson("pm1", "Hans", "Muster"));
		result.add(modelStore.newPerson("pm2", "Ruth", "Mueller"));
		result.add(modelStore.newPerson("pm3", "Heinz", "Kurz"));
		result.add(modelStore.newPerson("pm4", "Cornelia", "Meier"));
		result.add(modelStore.newPerson("pm5", "Werner", "Meyer"));
		result.add(modelStore.newPerson("pm6", "Lydia", "Kunz"));
		result.add(modelStore.newPerson("pm7", "Anna", "Best"));
		result.add(modelStore.newPerson("pm8", "Stefan", "Meier"));
		result.add(modelStore.newPerson("pm9", "Martin", "Mueller"));
		return result;
	}

}
