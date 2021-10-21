package com.innogames.junit5.scenariobuilder.example;

import com.innogames.junit5.scenariobuilder.ScenarioBuilder;
import com.innogames.junit5.scenariobuilder.ScenarioBuilderPart;
import com.innogames.junit5.scenariobuilder.ScenarioExtension;
import com.innogames.junit5.scenariobuilder.example.account.AccountBuilder;

import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Collection;
import java.util.List;

/**
 * ScenarioExtension for our example application what overrides the builder class
 * (See ScenarioExtensionWithCustomBuilderTest).
 */
public class AppScenarioExtensionWithCustomBuilder extends ScenarioExtension<GivenAppScenario> {

	@Override
	protected Collection<ScenarioBuilderPart<GivenAppScenario>> getBuilderParts(ExtensionContext extensionContext) {
		return List.of(
			new AccountBuilder()
		);
	}

	@Override
	protected GivenAppScenario createGivenScenario(ExtensionContext extensionContext) {
		return new GivenAppScenario();
	}

	@Override
	protected ScenarioBuilder<GivenAppScenario> createScenarioBuilder(ExtensionContext extensionContext) {
		return new AppScenario(extensionContext, this::createGivenScenario, getBuilderParts(extensionContext));
	}

}
