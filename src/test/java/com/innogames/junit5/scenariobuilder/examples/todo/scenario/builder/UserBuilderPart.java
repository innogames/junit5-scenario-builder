package com.innogames.junit5.scenariobuilder.examples.todo.scenario.builder;

import com.innogames.junit5.scenariobuilder.ScenarioBuilderPart;
import com.innogames.junit5.scenariobuilder.examples.todo.domain.User;
import com.innogames.junit5.scenariobuilder.examples.todo.scenario.given.GivenTodoScenario;

import java.util.UUID;

/**
 * Creates users that are defined in the GivenScenario.
 */
public class UserBuilderPart implements ScenarioBuilderPart<GivenTodoScenario> {

	@Override
	public int getOrder() {
		return BuilderPartOrder.USERS.ordinal();
	}

	@Override
	public void build(GivenTodoScenario givenScenario) {
		givenScenario.getUsers().forEach(givenUser -> {
			User user = new User();
			user.setId(UUID.randomUUID());
			user.setUsername(givenUser.getUsername());

			// ... Usually the user should be stored to the database here

			givenUser.setEntity(user);
		});
	}

}