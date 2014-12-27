package org.svenehrke.javafxdemos.address.model;

import org.svenehrke.javafxdemos.address.util.DateUtil;
import org.svenehrke.javafxdemos.infra.Attribute;
import org.svenehrke.javafxdemos.infra.ModelStore;
import org.svenehrke.javafxdemos.infra.PresentationModel;

public class Persons {
	public static Person fromPM(PresentationModel pm) {
		Person person = new Person();
		person.setId(pm.getId()); // todo: should PM-id and Entity-ID correspond or should PM have something like a persistentId ?
		person.setFirstName(pm.getAttribute(PersonAPI.ATT_FIRST_NAME).getValue());
		person.setLastName(pm.getAttribute(PersonAPI.ATT_LAST_NAME).getValue());
		person.setStreet(pm.getAttribute(PersonAPI.ATT_STREET).getValue());
		person.setPostalCode(Integer.parseInt(pm.getAttribute(PersonAPI.ATT_POSTAL_CODE).getValue()));
		person.setCity(pm.getAttribute(PersonAPI.ATT_CITY).getValue());
		person.setBirthday(DateUtil.parse((pm.getAttribute(PersonAPI.ATT_BIRTHDAY).getValue())));
		return person;
	}

	public static PresentationModel newPresentationModelFromPerson(ModelStore modelStore, Person p) {
		Attribute[] attributes = attributes(modelStore, p.getId(), p.getFirstName(), p.getLastName(), p.getStreet(), String.valueOf(p.getPostalCode()), p.getCity(), DateUtil.format(p.getBirthday()));
		return modelStore.newPresentationModel(p.getId(), PersonAPI.TYPE_PERSON, attributes);
	}

	public static Attribute[] attributes(ModelStore modelStore, String pmId, String firstname, String lastname, String street, String postalcode, String city, String birthday) {
		return new Attribute[] {
			newAttribute(modelStore, pmId, PersonAPI.ATT_FIRST_NAME, firstname),
			modelStore.newAttribute(PersonAPI.ATT_LAST_NAME, lastname, pmId + PersonAPI.ATT_LAST_NAME),
			modelStore.newAttribute(PersonAPI.ATT_STREET, street, pmId + PersonAPI.ATT_STREET),
			modelStore.newAttribute(PersonAPI.ATT_POSTAL_CODE, postalcode, pmId + PersonAPI.ATT_POSTAL_CODE),
			modelStore.newAttribute(PersonAPI.ATT_CITY, city, pmId + PersonAPI.ATT_CITY),
			modelStore.newAttribute(PersonAPI.ATT_BIRTHDAY, birthday, pmId + PersonAPI.ATT_BIRTHDAY)
		};
	}

	private static Attribute newAttribute(ModelStore modelStore, String pmId, String propertyName, String value) {
		return modelStore.newAttribute(propertyName, value, pmId + propertyName);
	}
}
