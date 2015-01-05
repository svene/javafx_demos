package org.svenehrke.javafxdemos.dynfilter;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.util.Callback;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class TasksTest {

	@Test
	public void test1() throws Exception {

		ObservableList<Task> allTasks = FXCollections.observableArrayList(Task.priorityExtractor());
//		ObservableList<Task> prio1Tasks;
		FilteredList<Task> prio1Tasks;

		Task taskA = new Task("1", Task.PRIO_2);
		Task taskB = new Task("2", Task.PRIO_1);

		prio1Tasks = new FilteredList<>(allTasks, task -> Task.PRIO_1.equals(task.priority.getValue()));
		allTasks.addAll(taskA, taskB);

		assertEquals(2, allTasks.size());
		assertEquals(1, prio1Tasks.size());
		assertSame(taskB, prio1Tasks.get(0));

		taskB.priority.setValue(Task.PRIO_2);
		assertEquals(2, allTasks.size());
		assertEquals(0, prio1Tasks.size());


		taskB.priority.setValue(Task.PRIO_1);
		prio1Tasks.setPredicate(task -> Task.PRIO_1.equals(task.priority.getValue())); // without this the test fails! -> bug in filteredList
		assertEquals(2, allTasks.size());
		assertEquals(1, prio1Tasks.size());
		assertSame(taskB, prio1Tasks.get(0));

		taskB.priority.setValue(Task.PRIO_2); // ArrayIndexOutOfBoundsException is thrown (and caught internally) leaving the lists in a corrupt state
	}
	@Test
	public void test2() throws Exception {

		ObservableList<Task> allTasks = FXCollections.observableArrayList(Task.priorityExtractor());
//		ObservableList<Task> prio1Tasks;
		FilteredList<Task> prio1Tasks;

		Task taskA = new Task("1", Task.PRIO_1);
		Task taskB = new Task("2", Task.PRIO_2);

		prio1Tasks = new FilteredList<>(allTasks, task -> Task.PRIO_1.equals(task.priority.getValue()));
		allTasks.addAll(taskA, taskB);

		assertEquals(2, allTasks.size());
		assertEquals(1, prio1Tasks.size());
		assertSame(taskA, prio1Tasks.get(0));

		taskA.priority.setValue(Task.PRIO_2);
		assertEquals(2, allTasks.size());
		assertEquals(0, prio1Tasks.size());


		taskA.priority.setValue(Task.PRIO_1);
		prio1Tasks.setPredicate(task -> Task.PRIO_1.equals(task.priority.getValue())); // without this the test fails! -> bug in filteredList
		assertEquals(2, allTasks.size());
		assertEquals(1, prio1Tasks.size());
		assertSame(taskA, prio1Tasks.get(0));

		taskA.priority.setValue(Task.PRIO_2); // ArrayIndexOutOfBoundsException is thrown (and caught internally) leaving the lists in a corrupt state
	}

	static class Task {

		public static final String PRIO_1 = "priority 1";
		public static final String PRIO_2 = "priority 2";

		final StringProperty name = new SimpleStringProperty();
		final StringProperty priority = new SimpleStringProperty();

		public Task(String name, String priority) {
			this.name.setValue(name);
			this.priority.setValue(priority);
		}

		static Callback<Task, Observable[]> priorityExtractor() {
			return (Task item) -> new Observable[] {item.priority};
		}
	}
}