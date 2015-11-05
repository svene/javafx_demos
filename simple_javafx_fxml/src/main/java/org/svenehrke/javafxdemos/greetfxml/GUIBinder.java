package org.svenehrke.javafxdemos.greetfxml;


import org.svenehrke.javafxdemos.infra.JavaFxWidgetBindings;

public class GUIBinder {

	private final GreetController controller;
	private final PresentationState presentationState;

	public GUIBinder(GreetController controller, PresentationState presentationState) {
		this.controller = controller;
		this.presentationState = presentationState;
	}

	void bindAndInitialize() {
		presentationState.setupBinding();
		setupWidgetBinding();
		setupActionListeners();
		presentationState.initData();
	}

	private void setupWidgetBinding() {
		JavaFxWidgetBindings.bindTextField(controller.nameTextField, presentationState.name);
		JavaFxWidgetBindings.bindLabel(controller.greetingLabel, presentationState.greeting);
	}

	private void setupActionListeners() {
		JavaFxWidgetBindings.bindButton(controller.greetingButton, ActionHandlers.greetHandler(presentationState));
	}
}
