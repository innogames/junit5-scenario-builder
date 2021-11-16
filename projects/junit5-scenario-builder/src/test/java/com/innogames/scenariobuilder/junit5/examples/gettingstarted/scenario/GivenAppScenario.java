package com.innogames.scenariobuilder.junit5.examples.gettingstarted.scenario;

import com.innogames.scenariobuilder.GivenScenario;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * The "root" scenario object in this example.
 */
public class GivenAppScenario implements GivenScenario {

	private final List<GivenUser> users = new ArrayList<>();

	public List<GivenUser> getUsers() {
		return users;
	}

	public GivenAppScenario withUser(Consumer<GivenUser> userConsumer) {
		var givenUser = new GivenUser();
		userConsumer.accept(givenUser);
		this.users.add(givenUser);
		return this;
	}

}