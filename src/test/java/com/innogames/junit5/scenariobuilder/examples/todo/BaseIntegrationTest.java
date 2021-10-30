package com.innogames.junit5.scenariobuilder.examples.todo;

import com.innogames.junit5.scenariobuilder.ScenarioAware;
import com.innogames.junit5.scenariobuilder.ScenarioBuilder;
import com.innogames.junit5.scenariobuilder.examples.todo.scenario.TodoScenarioExtension;
import com.innogames.junit5.scenariobuilder.examples.todo.scenario.given.GivenTodoScenario;

import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Base integration test class that all integration tests should extend
 */
@ExtendWith(TodoScenarioExtension.class)
public abstract class BaseIntegrationTest implements ScenarioAware<GivenTodoScenario> {

	protected ScenarioBuilder<GivenTodoScenario> scenarioBuilder;

	@Override
	public void setScenarioBuilder(ScenarioBuilder<GivenTodoScenario> builder) {
		scenarioBuilder = builder;
	}

}