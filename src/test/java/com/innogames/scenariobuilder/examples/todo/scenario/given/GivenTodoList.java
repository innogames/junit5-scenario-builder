package com.innogames.scenariobuilder.examples.todo.scenario.given;

import com.innogames.scenariobuilder.EntityRefHolder;
import com.innogames.scenariobuilder.examples.todo.domain.TodoList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Contains configuration for a TodoList in the test scenario.
 */
public class GivenTodoList extends EntityRefHolder<GivenTodoList, TodoList> {

	private final String name;
	private final List<GivenTask> todos = new ArrayList<>();

	public GivenTodoList(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<GivenTask> getTodos() {
		return todos;
	}

	public GivenTodoList withTodo(Consumer<GivenTask> todoConsumer) {
		var givenTodo = new GivenTask();
		todoConsumer.accept(givenTodo);
		this.todos.add(givenTodo);
		return this;
	}

	public GivenTodoList withTodo(String text) {
		var givenTodo = new GivenTask();
		givenTodo.withText(text);
		this.todos.add(givenTodo);
		return this;
	}

}