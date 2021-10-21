package com.innogames.junit5.scenariobuilder;

import com.innogames.junit5.scenariobuilder.example.AppScenarioExtensionWithCustomBuilder;
import com.innogames.junit5.scenariobuilder.example.account.Account;
import com.innogames.junit5.scenariobuilder.example.AppScenario;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AppScenarioExtensionWithCustomBuilder.class)
class ScenarioExtensionWithCustomBuilderTest {

	@Test
	public void test_custom_builder_passed_to_test(AppScenario appScenario) {
		var aliceRef = new Ref<Account>();
		var bobRef = new Ref<Account>();

		appScenario.build(scenario -> scenario
			.withAccount(account -> account
				.ref(aliceRef)
				.withName("Alice")
			)
			.withAccount(account -> account
				.ref(bobRef)
				.withName("Bob")
			)
		);

		assertEquals("Alice", aliceRef.get().getName());
		assertEquals("Bob", bobRef.get().getName());
	}

}