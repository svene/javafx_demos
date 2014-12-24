package org.svenehrke.javafxdemos.address;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import org.svenehrke.javafxdemos.address.model.Person;
import org.svenehrke.javafxdemos.infra.ImpulseListeners;

import java.util.function.Function;

public class Model {
	public static enum EditMode {
		NEW, EDIT, UNDEFINED
	}

	private Stage primaryStage;

	private ObservableList<Person> people = FXCollections.observableArrayList();

	public IntegerProperty selectedModelIndex = new SimpleIntegerProperty(-1);

	public final Person currentPerson = newEmptyPerson();
	public final Person workPerson = newEmptyPerson();
	public final Person emptyPerson = newEmptyPerson();
	public final BooleanProperty okButtonClicked = new SimpleBooleanProperty();
	private final BooleanProperty editOkButtonClicked = new SimpleBooleanProperty();
	private final BooleanProperty newOkButtonClicked = new SimpleBooleanProperty();
	public final ObjectProperty<EditMode> editModeProperty = new SimpleObjectProperty<>(EditMode.UNDEFINED);

	public final BooleanProperty workPersonValid = new SimpleBooleanProperty();
	public final StringProperty validationMessage = new SimpleStringProperty();

	public final StringProperty applicationTitle = new SimpleStringProperty();

	public Model(Stage primaryStage) {

		this.primaryStage = primaryStage;

		// Update 'currentPerson', e.g. when table selection changes:
		selectedModelIndex.addListener((s, o, n) -> {
				if (n.intValue() >= 0) {
					Person person = getPeople().get(n.intValue());
					currentPerson.populateFromPerson(person);
				}
			}
		);

		// Update item in 'sampleData' to which 'currentPerson' corresponds to when 'currentPerson' changes (e.g. when it is edited):
		copyPropertyOnChange(currentPerson, Person::firstNameProperty);
		copyPropertyOnChange(currentPerson, Person::lastNameProperty);
		copyPropertyOnChange(currentPerson, Person::streetProperty);
		copyPropertyOnChange(currentPerson, Person::postalCodeProperty);
		copyPropertyOnChange(currentPerson, Person::cityProperty);
		copyPropertyOnChange(currentPerson, Person::birthdayProperty);

		ImpulseListeners.bindImpulseResetter(okButtonClicked); // triggers impulse: 'OK button clicked'
		editOkButtonClicked.bind(okButtonClicked.and(Bindings.equal(editModeProperty, EditMode.EDIT))); // OK button for mode EDIT triggered (derived from okButtonClicked)
		newOkButtonClicked.bind(okButtonClicked.and(Bindings.equal(editModeProperty, EditMode.NEW))); // OK button for mode NEW triggered (derived from okButtonClicked)

		ImpulseListeners.addImpulseListener(editOkButtonClicked, () -> {
			currentPerson.populateFromPerson(workPerson);
		});
		ImpulseListeners.addImpulseListener(newOkButtonClicked, () -> {
			getPeople().add(new Person().populateFromPerson(workPerson));
			selectedModelIndex.setValue(getPeople().size() - 1);
		});

		workPerson.allProperties().forEach(sp -> {
			sp.addListener((s,o,n) -> new EditPersonValidator(this).validate());
		});

		workPersonValid.bind(Bindings.equal(validationMessage, ""));
		validationMessage.addListener((s,o,n) -> {
			if ( validationMessage.getValue().isEmpty() ) return;
			System.out.println("error: " + n);
		});

		// Initial Data:
		applicationTitle.setValue("Address Application");
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public ObservableList<Person> getPeople() {
		return people;
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
	private void copyPropertyOnChange(Person sourcePerson, Function<Person, StringProperty> pf) {
		pf.apply(sourcePerson).addListener((s, o, n) -> {
			int idx = selectedModelIndex.get();
			pf.apply(getPeople().get(idx)).setValue(n);
		});
	}

}
