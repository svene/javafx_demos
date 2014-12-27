
package org.svenehrke.javafxdemos.address.model;

import javax.xml.bind.annotation.XmlAttribute;
import java.time.LocalDate;

public class Person {

	private String id;
	private String firstName;
	private String lastName;
	private String street;
	private int postalCode;
	private String city;
	private LocalDate birthday;

/*
	public Person(String id, Attribute firstNameAttribute, Attribute lastNameAttribute) {
		this.id = id;
		this.firstName = firstNameAttribute;
		this.lastName = lastNameAttribute;

		// Some initial dummy data, just for convenient testing.
		this.street = modelStore.newAttribute("some street", "street", id + "street");
		this.postalCode = modelStore.newAttribute("1234", "postalcode", id + "postalcode");
		this.city = modelStore.newAttribute("some city", "city", id + "city");
		this.birthday = modelStore.newAttribute("21.02.1999", "birthday", id + "birthday");
	}
*/

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlAttribute
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@XmlAttribute
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@XmlAttribute
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@XmlAttribute
	public int getPostalCode() {
		return postalCode;
//		return Integer.parseInt(postalCode.getValue());
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
//		this.postalCode = .setValue(String.valueOf(postalCode));
	}

	@XmlAttribute
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@XmlAttribute
	public LocalDate getBirthday() {
		return birthday;
//		return DateUtil.parse(birthday.getValue());
	}

	public void setBirthday(LocalDate birthday) {
//		this.birthday = .setValue(DateUtil.format(birthday));
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Person{" +
			"id='" + id + '\'' +
			", firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			", street='" + street + '\'' +
			", postalCode=" + postalCode +
			", city='" + city + '\'' +
			", birthday=" + birthday +
			'}';
	}
}
