package com.innogames.scenariobuilder.junit5.examples.todo.scenario.given;

import com.innogames.scenariobuilder.EntityRefHolder;
import com.innogames.scenariobuilder.junit5.examples.todo.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Contains configuration for a user in the test scenario.
 */
public class GivenUser extends EntityRefHolder<GivenUser, User> {

	private String username = "testUser";
	private final List<GivenTodoList> todoLists = new ArrayList<>();

	public String getUsername() {
		return username;
	}

	public List<GivenTodoList> getTodoLists() {
		return todoLists;
	}

	public GivenUser withUsername(String username) {
		this.username = username;
		return this;
	}

	public GivenUser withTodoList(String name, Consumer<GivenTodoList> todoListConsumer) {
		var givenTodoList = new GivenTodoList(name);
		todoListConsumer.accept(givenTodoList);
		this.todoLists.add(givenTodoList);
		return this;
	}

}