package com.innogames.junit5.scenariobuilder;

import com.innogames.junit5.scenariobuilder.example.AppScenarioExtension;
import com.innogames.junit5.scenariobuilder.example.GivenAppScenario;
import com.innogames.junit5.scenariobuilder.example.account.Account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AppScenarioExtension.class)
class ScenarioExtensionTest {

	@Test
	public void test_builder_passed_to_test(ScenarioBuilder<GivenAppScenario> scenarioBuilder) {
		var aliceRef = new Ref<Account>();
		var bobRef = new Ref<Account>();

		scenarioBuilder.build(scenario -> scenario
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