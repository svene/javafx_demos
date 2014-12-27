package org.svenehrke.javafxdemos.address.model;

import org.svenehrke.javafxdemos.infra.Attribute;
import org.svenehrke.javafxdemos.infra.ModelStore;
import org.svenehrke.javafxdemos.infra.PresentationModel;

import java.util.ArrayList;
import java.util.List;

public class SampleData {

	public static void createSampleData(ModelStore modelStore) {
		List<PresentationModel> result = new ArrayList<>();
		result.add(newPreparedPresentationModel(modelStore, "pm1", "Hans", "Muster"));
		result.add(newPreparedPresentationModel(modelStore, "pm2", "Ruth", "Mueller"));
		result.add(newPreparedPresentationModel(modelStore, "pm3", "Heinz", "Kurz"));
		result.add(newPreparedPresentationModel(modelStore, "pm4", "Cornelia", "Meier"));
		result.add(newPreparedPresentationModel(modelStore, "pm5", "Werner", "Meyer"));
		result.add(newPreparedPresentationModel(modelStore, "pm6", "Lydia", "Kunz"));
		result.add(newPreparedPresentationModel(modelStore, "pm7", "Anna", "Best"));
		result.add(newPreparedPresentationModel(modelStore, "pm8", "Stefan", "Meier"));
		result.add(newPreparedPresentationModel(modelStore, "pm9", "Martin", "Mueller"));
	}

	public static PresentationModel newEmptyPresentationModel(ModelStore modelStore, String pmId) {
		return modelStore.newPresentationModel(pmId, PersonAPI.TYPE_PERSON, newEmptyAttributes(modelStore, pmId));
	}
	public static Attribute[] newEmptyAttributes(ModelStore modelStore, String pmId) {
		return Persons.attributes(modelStore, pmId, "", "", "", "", "", "");
	}

	public static Attribute[] preparedAttributes(ModelStore modelStore, String pmId, String firstname, String lastname) {
		return Persons.attributes(modelStore, pmId, firstname, lastname, "some street", "1234", "some city", "21.02.1999");
	}

	private static PresentationModel newPreparedPresentationModel(ModelStore modelStore, String pm1, String firstName, String lastName) {
		return modelStore.newPresentationModel(pm1, PersonAPI.TYPE_PERSON, preparedAttributes(modelStore, pm1, firstName, lastName));
	}

}
