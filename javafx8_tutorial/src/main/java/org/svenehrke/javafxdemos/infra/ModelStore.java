package org.svenehrke.javafxdemos.infra;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.svenehrke.javafxdemos.address.model.Person;

import java.util.*;

public class ModelStore {

	private ObservableList<Person> people = FXCollections.observableArrayList();

	private final Map<String, List<Attribute>> attributesPerQualifier = new HashMap<>();

	public static String newId() {
		return UUID.randomUUID().toString();
	}

	public ObservableList<Person> getPeople() {
		return people;
	}

	public Person newEmptyPerson() {
		Person result = newPerson(ModelStore.newId(), "", "");
		result.setCity("");
		result.setPostalCode(0);
		result.setStreet("");
		return result;
	}

	public Person newPerson(String pmId, String firstName, String lastName) {
		return new Person(pmId, lastName, newAttribute(firstName, pmId + "_firstname") );
	}
	public Attribute newAttribute(String value, String qualifier) {
		Attribute result = new Attribute(value, qualifier);
		attributesPerQualifier.putIfAbsent(qualifier, new ArrayList<>());
		attributesPerQualifier.get(qualifier).add(result);

		// If value of new attribute 'result' changes: find all attribute with the same qualifier and change their value accordingly:

		result.getValueProperty().addListener((s,o,n) -> {
			findAllAttributesPerQualifier(result.getQualifier()).forEach(it -> {
				//System.out.printf("result: %s%nit: %s %n", result, it );
				it.setValue(n);
			});
		});

		return result;
	}

	public List<Attribute> findAllAttributesPerQualifier(String qualifier) {
		return attributesPerQualifier.getOrDefault(qualifier, Collections.emptyList());
	}
}
