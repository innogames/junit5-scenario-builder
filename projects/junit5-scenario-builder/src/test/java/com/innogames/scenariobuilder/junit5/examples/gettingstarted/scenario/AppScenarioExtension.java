package com.innogames.scenariobuilder.junit5.examples.gettingstarted.scenario;

import com.innogames.scenariobuilder.ScenarioBuilderPart;
import com.innogames.scenariobuilder.junit5.ScenarioExtension;

import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Collection;
import java.util.List;

/**
 * ScenarioExtension for this example that is used by the tests.
 */
public class AppScenarioExtension extends ScenarioExtension<GivenAppScenario> {

	@Override
	protected Collection<ScenarioBuilderPart<GivenAppScenario>> getBuilderParts(ExtensionContext extensionContext) {
		return List.of(
			new UserBuilderPart()
		);
	}

	@Override
	protected GivenAppScenario createGivenScenario(ExtensionContext extensionContext) {
		return new GivenAppScenario();
	}

}