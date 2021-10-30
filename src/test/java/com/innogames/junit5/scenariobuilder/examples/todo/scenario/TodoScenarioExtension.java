package com.innogames.junit5.scenariobuilder.examples.todo.scenario;

import com.innogames.junit5.scenariobuilder.ScenarioBuilderPart;
import com.innogames.junit5.scenariobuilder.ScenarioExtension;
import com.innogames.junit5.scenariobuilder.examples.todo.scenario.builder.TaskBuilderPart;
import com.innogames.junit5.scenariobuilder.examples.todo.scenario.builder.TodoListBuilderPart;
import com.innogames.junit5.scenariobuilder.examples.todo.scenario.builder.UserBuilderPart;
import com.innogames.junit5.scenariobuilder.examples.todo.scenario.given.GivenTodoScenario;

import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Collection;
import java.util.List;

/**
 * Extension for the GivenTodoScenario.
 */
public class TodoScenarioExtension extends ScenarioExtension<GivenTodoScenario> {

	@Override
	protected Collection<ScenarioBuilderPart<GivenTodoScenario>> getBuilderParts(ExtensionContext extensionContext) {
		return List.of(
			new UserBuilderPart(),
			new TaskBuilderPart(),
			new TodoListBuilderPart()
		);
	}

	@Override
	protected GivenTodoScenario createGivenScenario(ExtensionContext extensionContext) {
		return new GivenTodoScenario();
	}

}
