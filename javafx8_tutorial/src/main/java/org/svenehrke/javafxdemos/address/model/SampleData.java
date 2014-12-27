package org.svenehrke.javafxdemos.address.model;

import org.svenehrke.javafxdemos.infra.Attribute;
import org.svenehrke.javafxdemos.infra.ModelStore;
import org.svenehrke.javafxdemos.infra.PresentationModel;

import java.util.ArrayList;
import java.util.List;

public class SampleData {

	public static void createSampleData(ModelStore modelStore) {
		List<PresentationModel> result = new ArrayList<>();
		result.add(presentationModel(modelStore, "pm1", "Hans", "Muster"));
		result.add(presentationModel(modelStore, "pm2", "Ruth", "Mueller"));
		result.add(presentationModel(modelStore, "pm3", "Heinz", "Kurz"));
		result.add(presentationModel(modelStore, "pm4", "Cornelia", "Meier"));
		result.add(presentationModel(modelStore, "pm5", "Werner", "Meyer"));
		result.add(presentationModel(modelStore, "pm6", "Lydia", "Kunz"));
		result.add(presentationModel(modelStore, "pm7", "Anna", "Best"));
		result.add(presentationModel(modelStore, "pm8", "Stefan", "Meier"));
		result.add(presentationModel(modelStore, "pm9", "Martin", "Mueller"));
		result.forEach(pm -> pm.addTag(PersonAPI.TYPE_PERSON));
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
