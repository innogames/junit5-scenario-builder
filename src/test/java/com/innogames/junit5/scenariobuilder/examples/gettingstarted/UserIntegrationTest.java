package com.innogames.junit5.scenariobuilder.examples.gettingstarted;

import com.innogames.junit5.scenariobuilder.Ref;
import com.innogames.junit5.scenariobuilder.examples.gettingstarted.domain.UserEntity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserIntegrationTest extends BaseIntegrationTest {

	@Test
	public void testLogin() {
		scenarioBuilder.build(scenario -> scenario
			.withUser(user -> user
				.withUsername("Christian")
				.withPassword("MySecretPassword")
			)
		);

		// Usually you would call a login endpoint here with username and password
		// and assert that the login was successful.
	}

	@Test
	public void testProfile() {
		var userRef = new Ref<UserEntity>();

		scenarioBuilder.build(scenario -> scenario
			.withUser(user -> user
				.ref(userRef)
				.withUsername("Christian")
			)
		);

		// To call an endpoint like "/profile/<userId>" you can get the generated
		// userId via `userRef.get().getId()`

		assertNotNull(userRef.get().getId());
	}

}