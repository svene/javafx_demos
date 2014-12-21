package org.svenehrke.javafxdemos.address.commandhandler;

import org.controlsfx.dialog.Dialogs;
import org.svenehrke.javafxdemos.address.Model;

public class DeletePersonCommandHandler implements Runnable {

	private final Model model;

	public DeletePersonCommandHandler(Model model) {
		this.model = model;
	}

	@Override
	public void run() {
		handleDelete(model);
	}

	public static void handleDelete(Model model1) {
		int selectedIndex = model1.selectedModelIndex.intValue();
		if (selectedIndex >= 0) {
			model1.getPersonData().remove(selectedIndex);
		} else {
			// Nothing selected.
			Dialogs.create()
				.title("No Selection")
				.masthead("No Person Selected")
				.message("Please select a person in the table.")
				.showWarning();
		}

	}



}
