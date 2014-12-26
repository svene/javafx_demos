package org.svenehrke.javafxdemos.address;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.svenehrke.javafxdemos.address.model.PersonAPI;
import org.svenehrke.javafxdemos.address.model.SampleData;
import org.svenehrke.javafxdemos.infra.ImpulseListeners;
import org.svenehrke.javafxdemos.infra.ModelStore;
import org.svenehrke.javafxdemos.infra.PresentationModel;

public class Model {
	public static enum EditMode {
		NEW, EDIT, UNDEFINED
	}

	private Stage primaryStage;

	public final StringProperty selectedPmId = new SimpleStringProperty();

	public final PresentationModel currentPerson;
	public final PresentationModel workPerson;
	public final BooleanProperty okButtonClicked = new SimpleBooleanProperty();
	private final BooleanProperty editOkButtonClicked = new SimpleBooleanProperty();
	private final BooleanProperty newOkButtonClicked = new SimpleBooleanProperty();
	public final ObjectProperty<EditMode> editModeProperty = new SimpleObjectProperty<>(EditMode.UNDEFINED);

	public final BooleanProperty workPersonValid = new SimpleBooleanProperty();
	public final StringProperty validationMessage = new SimpleStringProperty();

	public final StringProperty applicationTitle = new SimpleStringProperty();
	public final ObservableList<PresentationModel> personPresentationModels;
	public final ObservableList<PresentationModel> realPresentationModels;

	public Model(Stage primaryStage, ModelStore modelStore) {

		this.primaryStage = primaryStage;

		personPresentationModels = FXCollections.observableArrayList(extractor());
		Bindings.bindContent(personPresentationModels, modelStore.allPresentationModels());
		realPresentationModels = new FilteredList<>(personPresentationModels, pm -> pm.hasTag(PersonAPI.TAG_REAL));

		currentPerson = modelStore.newPresentationModel("current", SampleData.attributes(modelStore, "current", "", ""));
		workPerson = modelStore.newPresentationModel("work", SampleData.attributes(modelStore, "work", "", ""));

		// Update 'currentPerson', e.g. when table selection changes:
		selectedPmId.addListener((s, o, n) -> {
				PresentationModel pm = modelStore.getPm(selectedPmId.getValue());
				System.out.println("---");
				realPresentationModels.forEach(pm1 -> System.out.printf("'%s: %s/%s'%n", pm1.getId(), pm1.getAttribute(PersonAPI.ATT_FIRST_NAME).getValue(), pm1.tag.getValue()));
				System.out.println("---");
				modelStore.allPresentationModels().forEach(pm1 -> System.out.printf("'%s: %s/%s'%n", pm1.getId(), pm1.getAttribute(PersonAPI.ATT_FIRST_NAME).getValue(), pm1.tag.getValue()));
				currentPerson.populateFromPresentationModel(pm, true);
			}
		);

		ImpulseListeners.bindImpulseResetter(okButtonClicked); // triggers impulse: 'OK button clicked'
		editOkButtonClicked.bind(okButtonClicked.and(Bindings.equal(editModeProperty, EditMode.EDIT))); // OK button for mode EDIT triggered (derived from okButtonClicked)
		newOkButtonClicked.bind(okButtonClicked.and(Bindings.equal(editModeProperty, EditMode.NEW))); // OK button for mode NEW triggered (derived from okButtonClicked)

		ImpulseListeners.addImpulseListener(editOkButtonClicked, () -> {
			currentPerson.populateFromPresentationModel(workPerson, false);
		});
		ImpulseListeners.addImpulseListener(newOkButtonClicked, () -> {
			PresentationModel pm = SampleData.presentationModel(modelStore, ModelStore.newId(), "", "");
			pm.populateFromPresentationModel(workPerson, false);
			pm.addTag(PersonAPI.TAG_REAL);
			selectedPmId.setValue(pm.getId());
		});

		workPerson.allAttributes().forEach(att -> {
			att.getValueProperty().addListener((s, o, n) -> new EditPersonValidator(this).validate());
		});

		workPersonValid.bind(Bindings.equal(validationMessage, ""));
		validationMessage.addListener((s,o,n) -> {
			if ( validationMessage.getValue().isEmpty() ) return;
			System.out.println("error: " + n);
		});

		// Initial Data:
		applicationTitle.setValue("Address Application");
	}

	private static Callback<PresentationModel, Observable[]> extractor() {
		return (PresentationModel pm) -> new Observable[] {
			pm.tag
		};
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public PresentationModel getCurrentPerson() {
		return currentPerson;
	}

	public PresentationModel getWorkPerson() {
		return workPerson;
	}

}
