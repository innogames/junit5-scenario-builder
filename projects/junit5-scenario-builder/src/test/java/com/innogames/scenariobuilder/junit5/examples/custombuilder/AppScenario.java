package com.innogames.scenariobuilder.junit5.examples.custombuilder;

import com.innogames.scenariobuilder.GivenScenarioFactory;
import com.innogames.scenariobuilder.ScenarioBuilder;
import com.innogames.scenariobuilder.ScenarioBuilderPart;
import com.innogames.scenariobuilder.junit5.examples.gettingstarted.scenario.GivenAppScenario;

import java.util.Collection;

/**
 * This class extends the {@link ScenarioBuilder} just for the purpose of
 * making tests more readable by reducing verbosity.
 *
 * In tests, you can always refer to "AppScenario" instead of "ScenarioBuilder&lt;GivenAppScenario&gt;"
 */
public class AppScenario extends ScenarioBuilder<GivenAppScenario> {

	public AppScenario(GivenScenarioFactory<GivenAppScenario> givenScenarioFactory,
					   Collection<ScenarioBuilderPart<GivenAppScenario>> scenarioBuilderParts) {
		super(givenScenarioFactory, scenarioBuilderParts);
	}

}