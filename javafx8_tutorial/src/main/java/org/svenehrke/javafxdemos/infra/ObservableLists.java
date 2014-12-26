package org.svenehrke.javafxdemos.infra;

import javafx.collections.ObservableList;

import java.util.function.Predicate;

public class ObservableLists {
	public static <T> int indexForItem(ObservableList<T> list, Predicate<T> p) {
		for (int i = 0; i < list.size(); i++) {
			T item = list.get(i);
			if (p.test(item)) {
				return i;
			}
		}
		return -1;
	}
}
