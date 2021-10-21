package com.innogames.junit5.scenariobuilder;

import com.innogames.junit5.scenariobuilder.example.GivenAppScenario;
import com.innogames.junit5.scenariobuilder.example.account.Account;
import com.innogames.junit5.scenariobuilder.example.account.AccountBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScenarioBuilderTest {

	private ScenarioBuilder<GivenAppScenario> scenarioBuilder;

	private final List<String> texts = new ArrayList<>();

	@BeforeEach
	void setUp() {
		ExtensionContext extensionContext = Mockito.mock(ExtensionContext.class);

		scenarioBuilder = new ScenarioBuilder<>(
			extensionContext,
			context -> new GivenAppScenario(),
			List.of(
				new CollectAccountNames(texts),
				new AccountBuilder(),
				new CleanupTestPart(texts)
			)
		);
	}

	@Test
	public void test_build() {
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

		// assert that refs are filled correctly
		assertEquals("Alice", aliceRef.get().getName());
		assertEquals("Bob", bobRef.get().getName());

		// assert that builder parts were called
		assertEquals(3, texts.size());
		assertTrue(texts.contains("Alice"));
		assertTrue(texts.contains("Bob"));
		assertEquals("CleanupTestPart.build was called", texts.get(2));
	}

	@Test
	public void test_cleanup_not_called_if_no_scenario_built() {
		scenarioBuilder.cleanup();
		assertTrue(texts.isEmpty());
	}

	@Test
	public void test_cleanup() {
		// just call build first to make sure a scenario exists
		scenarioBuilder.build(s -> {
		});
		texts.clear();

		scenarioBuilder.cleanup();

		// assert that cleanup methods in builder parts were called in correct order
		assertEquals(2, texts.size());
		assertEquals("CleanupTestPart.cleanup was called", texts.get(0));
		assertEquals("cleanup by CollectAccountNames", texts.get(1));
	}

	/**
	 * Builder part just for testing purposes. It uses the Account entities that were created
	 * by the AccountBuilder and adds their names to the passed texts array.
	 */
	private static class CollectAccountNames implements ScenarioBuilderPart<GivenAppScenario> {

		private final List<String> texts;

		private CollectAccountNames(List<String> texts) {
			this.texts = texts;
		}

		@Override
		public int getOrder() {
			return 2;
		}

		@Override
		public void build(GivenAppScenario givenScenario) {
			givenScenario.getAccounts().forEach(givenAccount -> {
				Account account = givenAccount.getEntity();
				texts.add(account.getName());
			});
		}

		@Override
		public void cleanup(GivenAppScenario givenScenario) {
			texts.add("cleanup by CollectAccountNames");
		}

	}

	/**
	 * Builder part just for testing purposes. It adds a cleanup note to the passed texts array.
	 */
	private static class CleanupTestPart implements ScenarioBuilderPart<GivenAppScenario> {

		private final List<String> texts;

		private CleanupTestPart(List<String> texts) {
			this.texts = texts;
		}

		@Override
		public int getOrder() {
			return 3;
		}

		@Override
		public void build(GivenAppScenario givenScenario) {
			texts.add("CleanupTestPart.build was called");
		}

		@Override
		public void cleanup(GivenAppScenario givenScenario) {
			texts.add("CleanupTestPart.cleanup was called");
		}

	}

}