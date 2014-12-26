package org.svenehrke.javafxdemos.infra;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.svenehrke.javafxdemos.address.model.SampleData;

import java.util.*;

public class ModelStore {

	ObservableList<PresentationModel> presentationModels = FXCollections.observableArrayList();

	private final Map<String, PresentationModel> presentationModelsById = new HashMap<>();
	private final Map<String, List<Attribute>> attributesPerQualifier = new HashMap<>();

	public static String newId() {
		return UUID.randomUUID().toString();
	}

	public void clear() {
		presentationModels.clear();
		presentationModels.clear();
		attributesPerQualifier.clear();
	}

	public PresentationModel newEmptyPerson() {
		return newPresentationModel(newId(), SampleData.attributes(this, newId(), "", ""));
	}

	public PresentationModel newPresentationModel(String id, Attribute...attributes) {
		PresentationModel result = new PresentationModel(id, attributes);
		presentationModels.add(result);
		presentationModelsById.put(id, result);
		return result;
	}

	public Attribute newAttribute(String value, String propertyName, String qualifier) {
		Attribute result = new Attribute(value, propertyName, qualifier);
		attributesPerQualifier.putIfAbsent(qualifier, new ArrayList<>());
		attributesPerQualifier.get(qualifier).add(result);

		// If value of new attribute 'result' changes: find all attribute with the same qualifier and change their value accordingly:

		result.getValueProperty().addListener((s,o,n) -> {
			findAllAttributesPerQualifier(result.getQualifier()).forEach(it -> {
				//System.out.printf("result: %s%nit: %s %n", result, it );
				it.setValue(n);
			});
		});

		return result;
	}

	public ObservableList<PresentationModel> allPresentationModels() {
		return presentationModels;
	}

	public PresentationModel getPm(String id) {
		return presentationModelsById.get(id);
	}

	public List<Attribute> findAllAttributesPerQualifier(String qualifier) {
		return attributesPerQualifier.getOrDefault(qualifier, Collections.emptyList());
	}
}
