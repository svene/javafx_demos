package org.svenehrke.javafxdemos.greetfxml;

import org.junit.Before;
import org.junit.Test;
import org.svenehrke.fxmlguis.infra.JavaFxWidgetBindings;

import static org.junit.Assert.*;

public class PresentationStateTest {

	private PresentationState presentationState;

	@Before
	public void init() throws Exception {
		presentationState = new PresentationState();
		presentationState.initBinding();
	}

	@Test
	public void new_name_changes_greeting() throws Exception {

		// greeting has no value at the beginning:
		assertEquals(null, presentationState.greeting.getValue());

		// When 'Duke' is entered in TextField:
		presentationState.name.setValue("Duke");
		// the greeting is still not set:
		assertEquals(null, presentationState.greeting.getValue());

		// When the button is pressed:
		JavaFxWidgetBindings.triggerAction(ActionHandlers.greetHandler(presentationState));

		// the greeting is populated:
		assertEquals("Hello Duke", presentationState.greeting.getValue());
	}

}