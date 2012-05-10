package com.google.code.tinypmclient.internal.builder;

import com.google.code.tinypmclient.Task;

/**
 * Task builder with fluent interface.
 * @author Marcin Niebudek
 */
public class TaskBuilder {
 
	private Task task;

	private TaskBuilder() {
		task = new Task();
	}
	
	private TaskBuilder(int id) {
		this();
		task.setId(id);
	}

	public static TaskBuilder task() {
		return new TaskBuilder();
	}
	
	public static TaskBuilder task(int id) {
		return new TaskBuilder(id);
	}

	public Task build() {
		return task;
	}
	
}
