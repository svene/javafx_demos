package org.svenehrke.javafxdemos.table.tablepopulation;

import java.util.AbstractList;
import java.util.Collection;

public class FakeCollections {
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
	public static Collection<Integer> integerItems(int howMany) {
		return new AbstractList<Integer>() {

			@Override
			public Integer get(final int index) {
				return index;
			}

			@Override
			public int size() {
				return howMany;
			}
		};
	}
}
