package com.innogames.scenariobuilder.junit5.examples.custombuilder;

import com.innogames.scenariobuilder.Ref;
import com.innogames.scenariobuilder.junit5.examples.gettingstarted.domain.UserEntity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AppScenarioExtensionWithCustomBuilder.class)
public class CustomScenarioBuilderTest {

	@Test
	public void test_custom_builder_passed_to_test(AppScenario appScenario) {
		var aliceRef = new Ref<UserEntity>();
		var bobRef = new Ref<UserEntity>();

		appScenario.build(scenario -> scenario
			.withUser(user -> user
				.ref(aliceRef)
				.withUsername("alice")
			)
			.withUser(user -> user
				.ref(bobRef)
				.withUsername("bob")
			)
		);

		assertEquals("alice", aliceRef.get().getUsername());
		assertEquals("bob", bobRef.get().getUsername());
	}

}
