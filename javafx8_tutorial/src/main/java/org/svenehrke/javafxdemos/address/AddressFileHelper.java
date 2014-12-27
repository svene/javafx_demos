package org.svenehrke.javafxdemos.address;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import org.controlsfx.dialog.Dialogs;
import org.svenehrke.javafxdemos.address.model.Person;
import org.svenehrke.javafxdemos.address.model.PersonListWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.prefs.Preferences;

public class AddressFileHelper {

	/**
	 * Loads person data from the specified file. The current person data will
	 * be replaced.
	 *
	 * @param people
	 * @param applicationTitle
	 * @param file
	 */
	public void loadPersonDataFromFile(File file, ObservableList<Person> people, StringProperty applicationTitle) {
		try {
			JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			// Reading XML from the file and unmarshalling.
			System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
			PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

//			people.clear();
			List<Person> persons = wrapper.getPersons();
			people.addAll(persons);

			// Save the file path to the registry.
			setPersonFilePath(file, applicationTitle);

		} catch (Exception e) { // catches ANY exception
			e.printStackTrace();
			Dialogs.create()
				.title("Error")
				.masthead("Could not load data from file:\n" + file.getPath())
				.showException(e);
		}
	}

	/**
	 * Sets the file path of the currently loaded file. The path is persisted in
	 * the OS specific registry.
	 *  @param file the file or null to remove the path
	 * @param applicationTitle
	 */
	public void setPersonFilePath(File file, StringProperty applicationTitle) {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());

			// Update the stage title.
			applicationTitle.setValue("AddressApp - " + file.getName());
		} else {
			prefs.remove("filePath");

			// Update the stage title.
			applicationTitle.setValue("AddressApp");
		}
	}

	/**
	 * Returns the person file preference, i.e. the file that was last opened.
	 * The preference is read from the OS specific registry. If no such
	 * preference can be found, null is returned.
	 *
	 * @return
	 */
	public File getPersonFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		String filePath = prefs.get("filePath", null);
		return filePath == null ? null : new File(filePath);
	}
}
