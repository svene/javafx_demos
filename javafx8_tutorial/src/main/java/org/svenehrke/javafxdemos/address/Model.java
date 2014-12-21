package org.svenehrke.javafxdemos.address;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import org.svenehrke.javafxdemos.address.model.Person;
import org.svenehrke.javafxdemos.address.model.SampleData;
import org.svenehrke.javafxdemos.infra.ImpulseListeners;

public class Model {

	private SampleData sampleData;

	public IntegerProperty selectedModelIndex = new SimpleIntegerProperty(-1);

	public final Person currentPerson = newEmptyPerson();
	public final Person workPerson = newEmptyPerson();
	public final BooleanProperty editOkButtonClicked = new SimpleBooleanProperty();

	public Model() {
		sampleData = new SampleData();

		// Update 'currentPerson', e.g. when table selection changes:
		selectedModelIndex.addListener((s, o, n) -> {
				if (n.intValue() >= 0) {
					System.out.println("populated");
					Person person = getPersonData().get(n.intValue());
					currentPerson.populateFromPerson(person);
				}
			}
		);

		// Update item in 'sampleData' to which 'currentPerson' corresponds to when 'currentPerson' changes (e.g. when it is edited):
		currentPerson.firstNameProperty().addListener((s,o,n) -> {
			int idx = selectedModelIndex.get();
			getPersonData().get(idx).firstNameProperty().setValue(n);
		});
		currentPerson.lastNameProperty().addListener((s,o,n) -> {
			int idx = selectedModelIndex.get();
			getPersonData().get(idx).lastNameProperty().setValue(n);
		});
		currentPerson.streetProperty().addListener((s,o,n) -> {
			int idx = selectedModelIndex.get();
			getPersonData().get(idx).streetProperty().setValue(n);
		});
		currentPerson.postalCodeProperty().addListener((s,o,n) -> {
			int idx = selectedModelIndex.get();
			getPersonData().get(idx).postalCodeProperty().setValue(n);
		});
		currentPerson.cityProperty().addListener((s,o,n) -> {
			int idx = selectedModelIndex.get();
			getPersonData().get(idx).cityProperty().setValue(n);
		});
		currentPerson.birthdayProperty().addListener((s,o,n) -> {
			int idx = selectedModelIndex.get();
			getPersonData().get(idx).birthdayProperty().setValue(n);
		});

		ImpulseListeners.bindImpulseListener(editOkButtonClicked, () -> {
			currentPerson.populateFromPerson(workPerson);
		});

	}

	public ObservableList<Person> getPersonData() {
		return sampleData.getPersonData();
	}

	public Person getCurrentPerson() {
		return currentPerson;
	}

	public Person getWorkPerson() {
		return workPerson;
	}

	private Person newEmptyPerson() {
		Person result = new Person("", "");
		result.setCity("");
		result.setPostalCode(0);
		result.setStreet("");
		return result;
	}

}
