package com.innogames.junit5.scenariobuilder.examples.custombuilder;

import com.innogames.junit5.scenariobuilder.ScenarioBuilder;
import com.innogames.junit5.scenariobuilder.ScenarioBuilderPart;
import com.innogames.junit5.scenariobuilder.examples.gettingstarted.scenario.GivenAppScenario;

import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Collection;
import java.util.function.Function;

/**
 * This class extends the {@link ScenarioBuilder} just for the purpose of
 * making tests more readable by reducing verbosity.
 *
 * In tests, you can always refer to "AppScenario" instead of "ScenarioBuilder&lt;GivenAppScenario&gt;"
 */
public class AppScenario extends ScenarioBuilder<GivenAppScenario> {

	public AppScenario(ExtensionContext extensionContext, Function<ExtensionContext, GivenAppScenario> givenScenarioFactory,
					   Collection<ScenarioBuilderPart<GivenAppScenario>> scenarioBuilderParts) {
		super(extensionContext, givenScenarioFactory, scenarioBuilderParts);
	}

}