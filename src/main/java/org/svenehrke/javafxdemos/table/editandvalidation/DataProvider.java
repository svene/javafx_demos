package org.svenehrke.javafxdemos.table.editandvalidation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class DataProvider {

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


}
