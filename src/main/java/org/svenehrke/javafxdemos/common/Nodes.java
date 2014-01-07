package org.svenehrke.javafxdemos.common;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ScrollBar;

public class Nodes {

	public static ScrollBar verticalScrollBarFrom(Node node) {
		return scrollBarFrom(node, Orientation.VERTICAL);
	}

	public static ScrollBar horizontalScrollBarFrom(Node node) {
		return scrollBarFrom(node, Orientation.HORIZONTAL);
	}

	private static ScrollBar scrollBarFrom(Node node, Orientation orientation) {
		for (Node n: node.lookupAll(".scroll-bar")) {
			if (n instanceof ScrollBar) {
				ScrollBar scrollBar = (ScrollBar) n;
				if (scrollBar.getOrientation().equals(orientation)) {
					return scrollBar;
				}
			}
		}
		return null;
	}
}
