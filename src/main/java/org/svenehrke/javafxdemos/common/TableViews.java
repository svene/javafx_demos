package org.svenehrke.javafxdemos.common;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import com.sun.javafx.scene.control.skin.TableViewSkin;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableView;

public class TableViews {

	public static TableViewInfo getTableViewInfo(TableView tableView) {
		TableViewSkin tableViewSkin = (TableViewSkin) tableView.getSkin();
		ObservableList<Node> children = tableViewSkin.getChildren();
		VirtualFlow virtualFlow = (VirtualFlow) children.get(1);
		TableHeaderRow tableHeaderRow = (TableHeaderRow) children.get(0);


		return new TableViewInfo(virtualFlow, tableHeaderRow);

	}

}

