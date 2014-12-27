package org.svenehrke.javafxdemos.infra;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class ModelStore {

	ObservableList<PresentationModel> presentationModels = FXCollections.observableArrayList();

	private final Map<String, PresentationModel> presentationModelsById = new HashMap<>();
	private final Map<String, List<Attribute>> attributesPerQualifier = new HashMap<>();

	public static String newId() {
		return UUID.randomUUID().toString();
	}

	public void clear() {
		presentationModels.clear();
		attributesPerQualifier.clear();
	}

	public PresentationModel newPresentationModel(String id, String type, Attribute... attributes) {
		PresentationModel result = new PresentationModel(id, attributes);
		result.setType(type);
		presentationModels.add(result);
		presentationModelsById.put(id, result);
		return result;
	}

	public Attribute newAttribute(String propertyName, String value, String qualifier) {
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
	public List<PresentationModel> presentationModelsWithType(String type) {
		return presentationModels.stream().filter(pm -> type.equals(pm.getType())).collect(toList());
	}

	public void removePresentationModel(String id) {
		PresentationModel pm = getPm(id);
		if (pm != null) {
			presentationModels.removeIf(it -> pm.getId().equals(it.getId()));
			presentationModelsById.remove(id);
			// for each attribute in pm remove it from attributesPerQualifier:
			pm.allAttributes().forEach(a1 -> {
				String q = a1.getQualifier();
				if (q != null) {
					List<Attribute> lst = attributesPerQualifier.get(q);
					lst.removeIf(a2 -> a2 == a1);
				}
			});
		}
	}

	public PresentationModel getPm(String id) {
		return presentationModelsById.get(id);
	}

	public List<Attribute> findAllAttributesPerQualifier(String qualifier) {
		return attributesPerQualifier.getOrDefault(qualifier, Collections.emptyList());
	}
}
