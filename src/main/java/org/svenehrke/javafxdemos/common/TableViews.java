package org.svenehrke.javafxdemos.common;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import com.sun.javafx.scene.control.skin.TableViewSkin;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.util.Pair;
import org.svenehrke.javafxdemos.table.tablepair.Person;

public class TableViews {

	public static TableViewInfo getTableViewInfo(TableView tableView) {
		TableViewSkin tableViewSkin = (TableViewSkin) tableView.getSkin();
		ObservableList<Node> children = tableViewSkin.getChildren();
		VirtualFlow virtualFlow = (VirtualFlow) children.get(1);
		TableHeaderRow tableHeaderRow = (TableHeaderRow) children.get(0);


		return new TableViewInfo(virtualFlow, tableHeaderRow);

	}

	public static void showTVInfo(final TableView<Person> tableView, String prefix) {
		VirtualFlow virtualFlow = getTableViewInfo(tableView).getVirtualFlow();
		int first = virtualFlow.getFirstVisibleCell().getIndex();
		int last = virtualFlow.getLastVisibleCell().getIndex();
		System.out.printf("%s: first: %s, last: %s, first in viewport: %s, last in viewport: %s%n",
			prefix, first, last, virtualFlow.getFirstVisibleCellWithinViewPort().getIndex(), virtualFlow.getLastVisibleCellWithinViewPort().getIndex());
	}

	public static Pair<Integer, Integer> getVisibleRange(TableView tableView) {
		VirtualFlow virtualFlow = getTableViewInfo(tableView).getVirtualFlow();
		try {
			return new Pair<>(virtualFlow.getFirstVisibleCellWithinViewPort().getIndex(), virtualFlow.getLastVisibleCellWithinViewPort().getIndex());
		} catch (Exception e) {
			return new Pair<>(-1, -1);
		}
	}

}

