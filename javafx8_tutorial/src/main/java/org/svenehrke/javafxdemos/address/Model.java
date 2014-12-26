package org.svenehrke.javafxdemos.address;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.stage.Stage;
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

	public Model(Stage primaryStage, ModelStore modelStore) {

		this.primaryStage = primaryStage;

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
			SampleData.presentationModel(modelStore, ModelStore.newId(), "", "").populateFromPresentationModel(workPerson, true);
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
