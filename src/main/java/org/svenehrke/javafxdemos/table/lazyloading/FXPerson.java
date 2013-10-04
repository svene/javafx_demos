package org.svenehrke.javafxdemos.table.lazyloading;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FXPerson {
	private final int rowIndex;
	private int dbId;
	private LazyLoadingDemo1.LoadState loadState;
	private final StringProperty firstName;
	private final StringProperty lastName;

	public FXPerson(final int rowIndex, final int dbId, final String firstName, final String lastName) {
		this.rowIndex = rowIndex;
		this.dbId = dbId;
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		loadState = LazyLoadingDemo1.LoadState.NOT_LOADED;
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

	public LazyLoadingDemo1.LoadState getLoadState() {
		return loadState;
	}

	public void setLoadState(final LazyLoadingDemo1.LoadState loadState) {
		this.loadState = loadState;
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
