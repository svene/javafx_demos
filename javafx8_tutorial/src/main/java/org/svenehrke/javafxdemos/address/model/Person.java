
package org.svenehrke.javafxdemos.address.model;

import javafx.beans.property.*;
import org.svenehrke.javafxdemos.address.util.DateUtil;

import javax.xml.bind.annotation.XmlAttribute;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Person {

	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty street;
	private StringProperty postalCode;
	private StringProperty city;
	private StringProperty birthday;

	public Person() {
		this(null, null);
	}
	public Person(String firstNameProperty, String lastNameProperty) {
		this.firstName = new SimpleStringProperty(firstNameProperty);
		this.lastName = new SimpleStringProperty(lastNameProperty);

		// Some initial dummy data, just for convenient testing.
		this.street = new SimpleStringProperty("some street");
		this.postalCode = new SimpleStringProperty("1234");
		this.city = new SimpleStringProperty("some city");
		this.birthday = new SimpleStringProperty("1999-02-21");
	}

	public Person populateFromPerson(Person other) {
		this.firstName.setValue(other.firstName.getValue());
		this.lastName.setValue(other.lastName.getValue());
		this.street.setValue(other.street.getValue());
		this.postalCode.setValue(other.postalCode.getValue());
		this.city.setValue(other.city.getValue());
		this.birthday.setValue(other.birthday.getValue());
		return this;
	}

	public List<StringProperty> allProperties() {
		return Arrays.asList(firstName, lastName, street, postalCode, city, birthday);
	}

	@XmlAttribute
	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	@XmlAttribute
	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public StringProperty lastNameProperty() {
		return lastName;
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
			"firstName=" + firstName.getValue() +
			", lastName=" + lastName.getValue() +
			", street=" + street.getValue() +
			", postalCode=" + postalCode.getValue() +
			", city=" + city.getValue() +
			", birthday=" + birthday.getValue() +
			'}';
	}
}
