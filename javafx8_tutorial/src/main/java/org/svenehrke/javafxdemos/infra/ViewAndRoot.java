package org.svenehrke.javafxdemos.infra;

public class ViewAndRoot<V, R> {
	private final V view;
	private final R root;

	ViewAndRoot(V view, R root) {
		this.view = view;
		this.root = root;
	}

	public V getView() {
		return view;
	}

	public R getRoot() {
		return root;
	}
}
