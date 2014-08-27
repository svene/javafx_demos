package org.svenehrke.javafxdemos.common;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import org.junit.Test;
import org.mockito.Mockito;

public class FireablePropertyTest {

	@Test
	public void demonstrate_that_listener_normally_is_not_notified_when_value_does_not_change() throws Exception {
		SimpleObjectProperty<String> sop = new SimpleObjectProperty<>();
		ChangeListener changeListener = Mockito.mock(ChangeListener.class);
		sop.addListener(changeListener);

		sop.setValue("one");
		sop.setValue("one");

		Mockito.verify(changeListener, Mockito.times(1)).changed(sop, null, "one");
		Mockito.verifyNoMoreInteractions(changeListener);
	}

	@Test
	public void test_2() throws Exception {
		FireableSimpleObjectProperty<String> sop = new FireableSimpleObjectProperty<>();
		ChangeListener changeListener = Mockito.mock(ChangeListener.class);
		sop.addListener(changeListener);

		sop.setValue("one");
		sop.fireValueChangedEvent();

		Mockito.verify(changeListener, Mockito.times(1)).changed(sop, null, "one");
		Mockito.verifyNoMoreInteractions(changeListener);
	}

	@Test
	public void test_3() throws Exception {
		BetterStringProperty sop = new BetterStringProperty();
		ChangeListener changeListener = Mockito.mock(ChangeListener.class);
		sop.addListener(changeListener);

		sop.setValue("one");
		sop.fireValueChangedEvent();

		Mockito.verify(changeListener, Mockito.times(1)).changed(sop, null, "one");
		Mockito.verifyNoMoreInteractions(changeListener);
	}

}
