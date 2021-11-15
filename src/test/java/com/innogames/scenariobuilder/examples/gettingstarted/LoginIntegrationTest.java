package com.innogames.scenariobuilder.examples.gettingstarted;

import com.innogames.scenariobuilder.ScenarioBuilder;
import com.innogames.scenariobuilder.examples.gettingstarted.scenario.AppScenarioExtension;
import com.innogames.scenariobuilder.examples.gettingstarted.scenario.GivenAppScenario;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * This test is mentioned in the "Getting started" section of the documentation.
 */
@ExtendWith(AppScenarioExtension.class)
public class LoginIntegrationTest {

	@Test
	public void testLogin(ScenarioBuilder<GivenAppScenario> scenarioBuilder) {
		scenarioBuilder.build(scenario -> scenario
			.withUser(user -> user
				.withUsername("Christian")
				.withPassword("MySecretPassword")
			)
		);

		// Usually you would call a login endpoint here with username and password
		// and assert that the login was successful.
	}

}
