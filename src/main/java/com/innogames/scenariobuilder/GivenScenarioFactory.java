package com.innogames.scenariobuilder;

/**
 * Functional interface for creating a new {@link GivenScenario} object.
 */
@FunctionalInterface
public interface GivenScenarioFactory<G extends GivenScenario> {

	G create();

}
