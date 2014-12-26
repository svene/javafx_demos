package org.svenehrke.javafxdemos.address;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

	public IntegerProperty selectedModelIndex = new SimpleIntegerProperty(-1);
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

	public Model(Stage primaryStage, ModelStore modelStore) {

		this.primaryStage = primaryStage;

		personPresentationModels = FXCollections.observableArrayList(extractor());
		Bindings.bindContent(personPresentationModels, modelStore.allPresentationModels());

		currentPerson = modelStore.newEmptyPerson();
		workPerson = modelStore.newEmptyPerson();

		// Update 'currentPerson', e.g. when table selection changes:
		selectedPmId.addListener((s, o, n) -> {
				PresentationModel pm = modelStore.getPm(selectedPmId.getValue());
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
			pm.populateFromPresentationModel(workPerson, true);
			pm.addTag(PersonAPI.TAG_REAL);
			selectedModelIndex.setValue(modelStore.allPresentationModels().size() - 1);
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
		return (PresentationModel pm) -> new Observable[]{
			pm.getAttribute(PersonAPI.ATT_FIRST_NAME).getValueProperty()
			,pm.getAttribute(PersonAPI.ATT_LAST_NAME).getValueProperty()
			,pm.tag
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
