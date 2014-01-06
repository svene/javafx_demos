package org.svenehrke.javafxdemos.common;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import com.sun.javafx.scene.control.skin.VirtualFlow;

/**
 * Provides access to internal controls of a {@link javafx.scene.control.TableView} which are not accessible
 * through {@link javafx.scene.control.TableView}'s API.
 *
 * Can be created with {@link vsa.vfx.control.table.jfxtables.TableViews#getTableViewInfo}
 */
public class TableViewInfo {

	/**
	 * See {@link #getVirtualFlow()}
	 */
	private final VirtualFlow virtualFlow;

	/**
	 * See {@link #getTableHeaderRow()}
	 */
	private final TableHeaderRow tableHeaderRow;

	/**
	 * Constructor
	 *
	 * @param virtualFlow {@link VirtualFlow} which will be accessible through {@link #getVirtualFlow()}
	 * @param tableHeaderRow {@link TableHeaderRow} which will be accessible through {@link #getTableHeaderRow()}
	 */
	TableViewInfo(final VirtualFlow virtualFlow, final TableHeaderRow tableHeaderRow) {
		this.virtualFlow = virtualFlow;
		this.tableHeaderRow = tableHeaderRow;
	}

	/**
	 * The {@link javafx.scene.control.TableView}'s {@link VirtualFlow}
	 */
	public VirtualFlow getVirtualFlow() {
		return virtualFlow;
	}

	/**
	 * The {@link javafx.scene.control.TableView}'s {@link TableHeaderRow}
	 */
	public TableHeaderRow getTableHeaderRow() {
		return tableHeaderRow;
	}
}
