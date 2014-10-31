package org.svenehrke.javafxdemos.address;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import org.svenehrke.javafxdemos.address.model.Person;
import org.svenehrke.javafxdemos.address.model.SampleData;

public class Model {

	private SampleData sampleData;

	public IntegerProperty selectedModelIndex = new SimpleIntegerProperty(-1);

	public Model() {
		sampleData = new SampleData();
	}

	public ObservableList<Person> getPersonData() {
		return sampleData.getPersonData();
	}

}
