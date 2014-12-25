
package org.svenehrke.javafxdemos.address.model;

import javafx.beans.property.*;
import org.svenehrke.javafxdemos.address.util.DateUtil;
import org.svenehrke.javafxdemos.infra.Attribute;
import org.svenehrke.javafxdemos.infra.ModelStore;

import javax.xml.bind.annotation.XmlAttribute;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Person {

	private String id;
	private Attribute firstName;
	private Attribute lastName;
	private StringProperty street;
	private StringProperty postalCode;
	private StringProperty city;
	private StringProperty birthday;

	public Person(String pmId, Attribute firstNameAttribute, Attribute lastNameAttribute) {
		this.id = pmId == null ? ModelStore.newId() : pmId;
		this.firstName = firstNameAttribute;
		this.lastName = lastNameAttribute;

		// Some initial dummy data, just for convenient testing.
		this.street = new SimpleStringProperty("some street");
		this.postalCode = new SimpleStringProperty("1234");
		this.city = new SimpleStringProperty("some city");
		this.birthday = new SimpleStringProperty("21.02.1999");
	}

	public Person populateFromPerson(Person other, boolean usingQualifier) {
		this.id = other.id;
		this.firstName.populateFromAttribute(other.firstName, usingQualifier);
		this.lastName.populateFromAttribute(other.lastName, usingQualifier);
		this.street.setValue(other.street.getValue());
		this.postalCode.setValue(other.postalCode.getValue());
		this.city.setValue(other.city.getValue());
		this.birthday.setValue(other.birthday.getValue());
		return this;
	}

	public List<StringProperty> allProperties() {
		return Arrays.asList(street, postalCode, city, birthday);
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlAttribute
	public String getFirstName() {
		return firstName.getValue();
	}

	public void setFirstName(String firstName) {
		this.firstName.setValue(firstName);
	}

	public StringProperty firstNameProperty() {
		return firstName.getValueProperty();
	}

	@XmlAttribute
	public String getLastName() {
		return lastName.getValueProperty().get();
	}

	public void setLastName(String lastName) {
		this.lastName.setValue(lastName);
	}

	public StringProperty lastNameProperty() {
		return lastName.getValueProperty();
	}

	@XmlAttribute
	public String getStreet() {
		return street.get();
	}

	public void setStreet(String street) {
		this.street.set(street);
	}

	public StringProperty streetProperty() {
		return street;
	}

	@XmlAttribute
	public int getPostalCode() {
		return Integer.parseInt(postalCode.get());
	}

	public void setPostalCode(int postalCode) {
		this.postalCode.set(String.valueOf(postalCode));
	}

	public StringProperty postalCodeProperty() {
		return postalCode;
	}

	@XmlAttribute
	public String getCity() {
		return city.get();
	}

	public void setCity(String city) {
		this.city.set(city);
	}

	public StringProperty cityProperty() {
		return city;
	}

	@XmlAttribute
	public LocalDate getBirthday() {
		return DateUtil.parse(birthday.get());
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday.set(DateUtil.format(birthday));
	}

	public StringProperty birthdayProperty() {
		return birthday;
	}

	@Override
	public String toString() {
		return "Person{" +
			"sysid=" + System.identityHashCode(this) +
			", id=" + id +
			", firstName=" + firstName.getValue() +
			", lastName=" + lastName.getValue() +
			", street=" + street.getValue() +
			", postalCode=" + postalCode.getValue() +
			", city=" + city.getValue() +
			", birthday=" + birthday.getValue() +
			'}';
	}
}
