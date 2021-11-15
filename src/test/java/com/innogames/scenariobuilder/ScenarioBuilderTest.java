package com.innogames.scenariobuilder;

import com.innogames.scenariobuilder.examples.gettingstarted.domain.UserEntity;
import com.innogames.scenariobuilder.examples.gettingstarted.scenario.GivenAppScenario;
import com.innogames.scenariobuilder.examples.gettingstarted.scenario.UserBuilderPart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScenarioBuilderTest {

	private ScenarioBuilder<GivenAppScenario> scenarioBuilder;

	private final List<String> texts = new ArrayList<>();

	@BeforeEach
	void setUp() {
		scenarioBuilder = new ScenarioBuilder<>(
			GivenAppScenario::new,
			List.of(
				new CollectUsernames(texts),
				new UserBuilderPart(),
				new CleanupTestPart(texts)
			)
		);
	}

	@Test
	public void test_build() {
		var aliceRef = new Ref<UserEntity>();
		var bobRef = new Ref<UserEntity>();

		scenarioBuilder.build(scenario -> scenario
			.withUser(user -> user
				.ref(aliceRef)
				.withUsername("Alice")
			)
			.withUser(user -> user
				.ref(bobRef)
				.withUsername("Bob")
			)
		);

		// assert that refs are filled correctly
		assertEquals("Alice", aliceRef.get().getUsername());
		assertEquals("Bob", bobRef.get().getUsername());

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
		assertEquals("cleanup by CollectUsernames", texts.get(1));
	}

	/**
	 * Builder part just for testing purposes. It uses the user entities that were created
	 * by the UserBuilderPart and adds their names to the passed texts array.
	 */
	private static class CollectUsernames implements ScenarioBuilderPart<GivenAppScenario> {

		private final List<String> texts;

		private CollectUsernames(List<String> texts) {
			this.texts = texts;
		}

		@Override
		public int getOrder() {
			return 2;
		}

		@Override
		public void build(GivenAppScenario givenScenario) {
			givenScenario.getUsers().forEach(givenUser ->
				texts.add(givenUser.getEntity().getUsername()));
		}

		@Override
		public void cleanup(GivenAppScenario givenScenario) {
			texts.add("cleanup by CollectUsernames");
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