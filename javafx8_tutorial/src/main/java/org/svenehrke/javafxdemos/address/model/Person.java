
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
	private Attribute street;
	private Attribute postalCode;
	private Attribute city;
	private Attribute birthday;

	public Person(ModelStore modelStore, String pmId, Attribute firstNameAttribute, Attribute lastNameAttribute) {
		this.id = pmId == null ? ModelStore.newId() : pmId;
		this.firstName = firstNameAttribute;
		this.lastName = lastNameAttribute;

		// Some initial dummy data, just for convenient testing.
		this.street = modelStore.newAttribute("some street", "street", pmId + "street");
		this.postalCode = modelStore.newAttribute("1234", "postalcode", pmId + "postalcode");
		this.city = modelStore.newAttribute("some city", "city", pmId + "city");
		this.birthday = modelStore.newAttribute("21.02.1999", "birthday", pmId + "birthday");
	}

	public List<StringProperty> allProperties() {
		return Arrays.asList(firstNameProperty(), lastNameProperty(), streetProperty(), postalCodeProperty(), cityProperty(), birthdayProperty());
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
		return lastName.getValue();
	}

	public void setLastName(String lastName) {
		this.lastName.setValue(lastName);
	}

	public StringProperty lastNameProperty() {
		return lastName.getValueProperty();
	}

	@XmlAttribute
	public String getStreet() {
		return street.getValue();
	}

	public void setStreet(String street) {
		this.street.setValue(street);
	}

	public StringProperty streetProperty() {
		return street.getValueProperty();
	}

	@XmlAttribute
	public int getPostalCode() {
		return Integer.parseInt(postalCode.getValue());
	}

	public void setPostalCode(int postalCode) {
		this.postalCode.setValue(String.valueOf(postalCode));
	}

	public StringProperty postalCodeProperty() {
		return postalCode.getValueProperty();
	}

	@XmlAttribute
	public String getCity() {
		return city.getValue();
	}

	public void setCity(String city) {
		this.city.setValue(city);
	}

	public StringProperty cityProperty() {
		return city.getValueProperty();
	}

	@XmlAttribute
	public LocalDate getBirthday() {
		return DateUtil.parse(birthday.getValue());
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday.setValue(DateUtil.format(birthday));
	}

	public StringProperty birthdayProperty() {
		return birthday.getValueProperty();
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
