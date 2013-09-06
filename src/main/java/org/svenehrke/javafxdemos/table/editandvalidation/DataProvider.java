package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

class DataProvider {

	static Stream<PersonTableBean> peopleStream(Function<String, ValidationResult> validator) {
		List<PersonTableBean> result = Arrays.asList(
			newPersonTableBean("Essie", "Vaill", "2.3", validator)
			, newPersonTableBean("Cruz", "Roudabush", "2.3", validator)
			, newPersonTableBean("Billie", "Tinnes", "2.3", validator)
			, newPersonTableBean("Zackary", "Mockus", "2.3", validator)
			, newPersonTableBean("Rosemarie", "Fifield", "2.3", validator)
			, newPersonTableBean("Bernard", "Laboy", "2.3", validator)
			, newPersonTableBean("Sue", "Haakinson", "2.3", validator)
			, newPersonTableBean("Valerie", "Pou", "2.3", validator)
			, newPersonTableBean("Lashawn", "Hasty", "2.3", validator)
			, newPersonTableBean("Marianne", "Earman", "2.3", validator)
			, newPersonTableBean("Justina", "Dragaj", "2.3", validator)
			, newPersonTableBean("Mandy", "Mcdonnell", "2.3", validator)
			, newPersonTableBean("Conrad", "Lanfear", "2.3", validator)
			, newPersonTableBean("Cyril", "Behen", "2.3", validator)
			, newPersonTableBean("Shelley", "Groden", "2.3", validator)
			, newPersonTableBean("Rosalind", "Krenzke", "2.3", validator)
			, newPersonTableBean("Davis", "Brevard", "2.3", validator)
		);
		return result.stream();
	}

	static PersonTableBean newPersonTableBean(String firstName, final String lastName, String value, Function<String, ValidationResult> firstnameValidator) {
		PersonTableBean result = new PersonTableBean("", "", "");
		ValidatedStrings.bindValidatorToValidatedString(result.firstName(), firstnameValidator);
		result.firstName().setText(firstName);
		result.setLastName(lastName);
		result.bigDecimalValue().setText(value);
		return result;
	}

	static Function<String, ValidationResult> firstNameValidator() {
		return value -> {
			boolean valid = value.length() > 3;
			return new ValidationResult(valid, valid ? "" : "error: Length of first name must be greater than 3");
		};
	}

	static ObservableList<PersonTableBean> people() {
		ObservableList<PersonTableBean> result = FXCollections.observableArrayList();
		peopleStream(firstNameValidator()).forEach(result::add);
		return result;
	}
}
