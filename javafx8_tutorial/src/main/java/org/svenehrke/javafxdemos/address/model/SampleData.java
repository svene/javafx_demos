package org.svenehrke.javafxdemos.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.svenehrke.javafxdemos.infra.Attribute;
import org.svenehrke.javafxdemos.infra.ModelStore;
import org.svenehrke.javafxdemos.infra.PresentationModel;

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

	public static void createSampleData(ModelStore modelStore) {
		presentationModel(modelStore, "pm1", "Hans", "Muster");
		presentationModel(modelStore, "pm2", "Ruth", "Mueller");
		presentationModel(modelStore, "pm3", "Heinz", "Kurz");
		presentationModel(modelStore, "pm4", "Cornelia", "Meier");
		presentationModel(modelStore, "pm5", "Werner", "Meyer");
		presentationModel(modelStore, "pm6", "Lydia", "Kunz");
		presentationModel(modelStore, "pm7", "Anna", "Best");
		presentationModel(modelStore, "pm8", "Stefan", "Meier");
		presentationModel(modelStore, "pm9", "Martin", "Mueller");
	}

	public static PresentationModel presentationModel(ModelStore modelStore, String pm1, String firstName, String lastName) {
		return modelStore.newPresentationModel(pm1, attributes(modelStore, pm1, firstName, lastName));
	}

	public static Attribute[] attributes(ModelStore modelStore, String pmId, String firstname, String lastname) {
		return new Attribute[] {
			modelStore.newAttribute(firstname, PersonAPI.ATT_FIRST_NAME, pmId + PersonAPI.ATT_FIRST_NAME),
			modelStore.newAttribute(lastname, PersonAPI.ATT_LAST_NAME, pmId + PersonAPI.ATT_LAST_NAME),
			modelStore.newAttribute("some street", PersonAPI.ATT_STREET, pmId + PersonAPI.ATT_STREET),
			modelStore.newAttribute("1234", PersonAPI.ATT_POSTAL_CODE, pmId + PersonAPI.ATT_POSTAL_CODE),
			modelStore.newAttribute("some city", PersonAPI.ATT_CITY, pmId + PersonAPI.ATT_CITY),
			modelStore.newAttribute("21.02.1999", PersonAPI.ATT_BIRTHDAY, pmId + PersonAPI.ATT_BIRTHDAY)
		};
	}

}
