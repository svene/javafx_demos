package org.svenehrke.javafxdemos.greetfxml;

import org.junit.Test;
import org.svenehrke.javafxdemos.infra.JavaFxWidgetBindings;

import static org.junit.Assert.*;

public class PresentationStateTest {

	@Test
	public void test1() throws Exception {
		PresentationState presentationState = new PresentationState();
		presentationState.setupBinding();

		assertEquals(null, presentationState.greeting.getValue());

		presentationState.name.setValue("Duke");
		assertEquals(null, presentationState.greeting.getValue());

		JavaFxWidgetBindings.trigger(presentationState.greetTrigger);

		assertEquals("Hello Duke", presentationState.greeting.getValue());
	}

}