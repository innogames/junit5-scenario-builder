package com.innogames.junit5.scenariobuilder;

import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Builds the test scenario based on the passed {@link GivenScenario} object.
 * This class calls all related {@link ScenarioBuilderPart}s in the correct order.
 * Note that a new ScenarioBuilder instance is created for each test.
 */
public class ScenarioBuilder<G extends GivenScenario> {

	private final ExtensionContext extensionContext;
	private final Function<ExtensionContext, G> givenScenarioFactory;
	private final Collection<ScenarioBuilderPart<G>> builderParts;

	private G givenScenario;

	public ScenarioBuilder(ExtensionContext extensionContext, Function<ExtensionContext, G> givenScenarioFactory,
						   Collection<ScenarioBuilderPart<G>> builderParts) {
		this.extensionContext = extensionContext;
		this.givenScenarioFactory = givenScenarioFactory;
		this.builderParts = builderParts;
	}

	public void build(Consumer<G> scenarioConsumer) {
		givenScenario = givenScenarioFactory.apply(extensionContext);
		scenarioConsumer.accept(givenScenario);

		builderParts.stream()
			.sorted(Comparator.comparingInt(ScenarioBuilderPart::getOrder))
			.forEach(builderPart -> builderPart.build(givenScenario));
	}

	public void cleanup() {
		if (givenScenario != null) {
			builderParts.stream()
				.sorted((a, b) -> b.getOrder() - a.getOrder())
				.forEach(builderPart -> builderPart.cleanup(givenScenario));
		}
	}

}
