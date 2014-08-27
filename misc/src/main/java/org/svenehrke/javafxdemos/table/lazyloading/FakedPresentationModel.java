package org.svenehrke.javafxdemos.table.lazyloading;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Simulation of an open-dolphin PM.
 */
public class FakedPresentationModel {
	private final int rowIndex;
	private int dbId;
	private final StringProperty firstName;
	private final StringProperty lastName;

	public FakedPresentationModel(final int rowIndex, final int dbId, final String firstName, final String lastName) {
		this.rowIndex = rowIndex;
		this.dbId = dbId;
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
	}
	public int getRowIndex() {
		return rowIndex;
	}

	public int getDbId() {
		return dbId;
	}

	public void setDbId(final int dbId) {
		this.dbId = dbId;
	}

	public String getFirstName() {
		return firstName.get();
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	public String getLastName() {
		return lastName.get();
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}
}
