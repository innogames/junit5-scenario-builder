package com.innogames.scenariobuilder.examples.gettingstarted;

import com.innogames.scenariobuilder.ScenarioBuilder;
import com.innogames.scenariobuilder.examples.gettingstarted.scenario.AppScenarioExtension;
import com.innogames.scenariobuilder.examples.gettingstarted.scenario.GivenAppScenario;
import com.innogames.scenariobuilder.junit5.ScenarioAware;

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