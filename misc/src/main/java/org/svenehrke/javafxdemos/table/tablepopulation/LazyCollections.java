package org.svenehrke.javafxdemos.table.tablepopulation;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;

public class LazyCollections {
	public static <S> Collection<S> items(int howMany) {
		return new AbstractList<S>() {

			@Override
			public S get(final int index) {
				return null;
			}

			@Override
			public int size() {
				return howMany;
			}
		};
	}
	public static List<Integer> integerItems(int howMany) {
		return new AbstractList<Integer>() {

			@Override
			public Integer get(final int index) {
				System.out.println(index);
				return index;
//				return index == 5 ? null : index;
			}

			@Override
			public int size() {
				return howMany;
			}
		};
	}

	public static <T> ObservableList<T> newObservableList(final List<T> integerList) {
		return new ObservableListWrapper<T>(integerList);
	}
}
