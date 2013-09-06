package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

class DataProvider {

	static PersonTableBeanBuilder newPersonTableBeanBuilder() {
		return new PersonTableBeanBuilder(firstNameValidator(), bigDecimalValidator());
	}

	static ObservableList<PersonTableBean> people(PersonTableBeanBuilder builder) {
		ObservableList<PersonTableBean> result = FXCollections.observableArrayList();
		peopleStream(builder).forEach(result::add);
		return result;
	}

	private static Stream<PersonTableBean> peopleStream(PersonTableBeanBuilder builder) {
		List<PersonTableBean> result = Arrays.asList(
			builder.newPersonTableBean("Essie", "Vaill", "2,3")
			, builder.newPersonTableBean("Cruz", "Roudabush", "2.3")
			, builder.newPersonTableBean("Billie", "Tinnes", "2,3")
			, builder.newPersonTableBean("Zackary", "Mockus", "2,3")
			, builder.newPersonTableBean("Rosemarie", "Fifield", "2,3")
			, builder.newPersonTableBean("Bernard", "Laboy", "2,3")
			, builder.newPersonTableBean("Sue", "Haakinson", "2,3")
			, builder.newPersonTableBean("Valerie", "Pou", "2,3")
			, builder.newPersonTableBean("Lashawn", "Hasty", "2,3")
			, builder.newPersonTableBean("Marianne", "Earman", "2,3")
			, builder.newPersonTableBean("Justina", "Dragaj", "2,3")
			, builder.newPersonTableBean("Mandy", "Mcdonnell", "2,3")
			, builder.newPersonTableBean("Conrad", "Lanfear", "2,3")
			, builder.newPersonTableBean("Cyril", "Behen", "2,3")
			, builder.newPersonTableBean("Shelley", "Groden", "2,3")
			, builder.newPersonTableBean("Rosalind", "Krenzke", "2,3")
			, builder.newPersonTableBean("Davis", "Brevard", "2,3")
		);
		return result.stream();
	}

	static Function<String, ValidationResult> firstNameValidator() {
		return value -> {
			boolean valid = value.length() > 3;
			return new ValidationResult(valid, valid ? "" : "Validation Error: Length of first name must be greater than 3");
		};
	}
	static Function<String, ValidationResult> bigDecimalValidator() {
		// Definition: the pattern for string value is the same as the constructor of BigDecimal requires, but instead of the '.' a ',' needs to be used
		// Examples:
		// '23': valid
		// '23,1': valid
		// '23.1': invalid

		// Idea:
		// 1. convenience converter helping user while editing or in Cell.updateItem to create correct format if possible. E.g.: '23.1' -> '23,1'
		// 2. validation converter to convert formatted String to String which can be used as input for validation: '23,1' -> '23.1' (for BigDecimal constructor)
		// 3. (not part of validation) a formatter to render a real value (BigDecimal) into a formatted string is needed for the initial String values. It's behavior is the reverse of 2.
		//    TODO: think about where 3. belongs to

		return value -> {
			ValidationResult vr;
			if (value.contains(".")) { // not needed when convenience validator is used
				vr = new ValidationResult(false, "Validation Error: '.' not allowed. use ',' instead.");
			}
			else {
				String bigDecimalString = value.replace(",", "."); // done by validation converter

				// Validation (2.):
				try {
					new BigDecimal(bigDecimalString); // throws NumberFormatException if not valid
					vr = ValidationResult.VALID;
				} catch (NumberFormatException e) {
					vr = new ValidationResult(false, e.getMessage());
				}
			}
			return vr;
		};
	}
}
