package org.svenehrke.javafxdemos.table.editandvalidation.persistence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository {

	private static PersonRepository instance = new PersonRepository();

	public static PersonRepository getInstance() {
		return instance;
	}

	private List<Person> people = new ArrayList<>();

	private PersonRepository() {
		people.add(newPerson("Essie", "Vaill", "2.3"));
		people.add(newPerson("Cruz", "Roudabush", "2.3"));
		people.add(newPerson("Billie", "Tinnes", "2.3"));
		people.add(newPerson("Zackary", "Mockus", "2.3"));
		people.add(newPerson("Rosemarie", "Fifield", "2.3"));
		people.add(newPerson("Bernard", "Laboy", "2.3"));
		people.add(newPerson("Sue", "Haakinson", "2.3"));
		people.add(newPerson("Valerie", "Pou", "2.3"));
		people.add(newPerson("Lashawn", "Hasty", "2.3"));
		people.add(newPerson("Marianne", "Earman", "2.3"));
		people.add(newPerson("Justina", "Dragaj", "2.3"));
		people.add(newPerson("Mandy", "Mcdonnell", "2.3"));
		people.add(newPerson("Conrad", "Lanfear", "2.3"));
		people.add(newPerson("Cyril", "Behen", "2.3"));
		people.add(newPerson("Shelley", "Groden", "2.3"));
		people.add(newPerson("Rosalind", "Krenzke", "2.3"));
		people.add(newPerson("Davis", "Brevard", "2.3"));
	}

	private Person newPerson(final String firstName, final String lastName, final String bdString) {
		return new Person(IdGenerator.next(), firstName, lastName, new BigDecimal(bdString));
	}

	public List<Person> people() {
		return people;
	}

}
