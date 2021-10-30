package com.innogames.junit5.scenariobuilder.examples.gettingstarted.scenario;

import com.innogames.junit5.scenariobuilder.ScenarioBuilderPart;
import com.innogames.junit5.scenariobuilder.ScenarioExtension;

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