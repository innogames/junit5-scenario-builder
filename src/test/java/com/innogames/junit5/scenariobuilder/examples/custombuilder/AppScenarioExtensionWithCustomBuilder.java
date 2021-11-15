package com.innogames.junit5.scenariobuilder.examples.custombuilder;

import com.innogames.junit5.scenariobuilder.ScenarioBuilder;
import com.innogames.junit5.scenariobuilder.ScenarioBuilderPart;
import com.innogames.junit5.scenariobuilder.ScenarioExtension;
import com.innogames.junit5.scenariobuilder.examples.gettingstarted.scenario.GivenAppScenario;
import com.innogames.junit5.scenariobuilder.examples.gettingstarted.scenario.UserBuilderPart;

import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Collection;
import java.util.List;

/**
 * ScenarioExtension that uses a custom {@link ScenarioBuilder} class to reduce
 * verbosity in tests.
 */
public class AppScenarioExtensionWithCustomBuilder extends ScenarioExtension<GivenAppScenario> {

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

	@Override
	protected ScenarioBuilder<GivenAppScenario> createScenarioBuilder(ExtensionContext extensionContext) {
		return new AppScenario(() -> createGivenScenario(extensionContext), getBuilderParts(extensionContext));
	}

}