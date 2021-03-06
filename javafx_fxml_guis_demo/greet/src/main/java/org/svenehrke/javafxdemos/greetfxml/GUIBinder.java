package org.svenehrke.javafxdemos.greetfxml;


import org.svenehrke.fxmlguis.guis.greet.GreetController;
import org.svenehrke.fxmlguis.infra.JavaFxWidgetBindings;

public class GUIBinder {

	private final GreetController controller;
	private final PresentationState presentationState;

	public GUIBinder(GreetController controller, PresentationState presentationState) {
		this.controller = controller;
		this.presentationState = presentationState;
	}

	void bindAndInitialize() {
		presentationState.initBinding();
		initWidgetBinding();
		initActionHandlers();
		presentationState.initData();
	}

	private void initWidgetBinding() {
		JavaFxWidgetBindings.bindTextField(controller.nameTextField, presentationState.name);
		JavaFxWidgetBindings.bindLabel(controller.greetingLabel, presentationState.greeting);
	}

	private void initActionHandlers() {
		JavaFxWidgetBindings.bindButton(controller.greetingButton, ActionHandlers.greetHandler(presentationState));
	}
}
