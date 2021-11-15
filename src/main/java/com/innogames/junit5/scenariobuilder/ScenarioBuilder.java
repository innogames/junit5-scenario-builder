package com.innogames.junit5.scenariobuilder;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Builds the scenario based on the passed {@link GivenScenario} object.
 * This class calls all related {@link ScenarioBuilderPart}s in the correct order.
 */
public class ScenarioBuilder<G extends GivenScenario> {

	private final GivenScenarioFactory<G> givenScenarioFactory;
	private final Collection<ScenarioBuilderPart<G>> builderParts;

	private G givenScenario;

	public ScenarioBuilder(GivenScenarioFactory<G> givenScenarioFactory, Collection<ScenarioBuilderPart<G>> builderParts) {
		this.givenScenarioFactory = givenScenarioFactory;
		this.builderParts = builderParts;
	}

	public void build(Consumer<G> scenarioConsumer) {
		givenScenario = givenScenarioFactory.create();
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
