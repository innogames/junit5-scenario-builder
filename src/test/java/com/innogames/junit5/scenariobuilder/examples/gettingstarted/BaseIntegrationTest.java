package com.innogames.junit5.scenariobuilder.examples.gettingstarted;

import com.innogames.junit5.scenariobuilder.ScenarioAware;
import com.innogames.junit5.scenariobuilder.ScenarioBuilder;
import com.innogames.junit5.scenariobuilder.examples.gettingstarted.scenario.AppScenarioExtension;
import com.innogames.junit5.scenariobuilder.examples.gettingstarted.scenario.GivenAppScenario;

import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Abstract test class that implements {@link ScenarioAware}
 * to get the Scenario Builder injected.
 */
@ExtendWith(AppScenarioExtension.class)
public abstract class BaseIntegrationTest implements ScenarioAware<GivenAppScenario> {

	protected ScenarioBuilder<GivenAppScenario> scenarioBuilder;

	@Override
	public void setScenarioBuilder(ScenarioBuilder<GivenAppScenario> builder) {
		scenarioBuilder = builder;
	}

}