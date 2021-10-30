package com.innogames.junit5.scenariobuilder.examples.todo.scenario.given;

import com.innogames.junit5.scenariobuilder.GivenScenario;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Root scenario for this example application
 */
public class GivenTodoScenario implements GivenScenario {

	private final List<GivenUser> users = new ArrayList<>();

	public List<GivenUser> getUsers() {
		return users;
	}

	public GivenTodoScenario withUser(Consumer<GivenUser> userConsumer) {
		var givenUser = new GivenUser();
		userConsumer.accept(givenUser);
		this.users.add(givenUser);
		return this;
	}

}