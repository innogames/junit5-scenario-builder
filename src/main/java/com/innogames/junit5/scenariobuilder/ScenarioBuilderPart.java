package com.innogames.junit5.scenariobuilder;

/**
 * Classes that implement this interface are responsible for building one part
 * of the whole test scenario.
 */
public interface ScenarioBuilderPart<G extends GivenScenario> {

	/**
	 * @return The order of this builder part. All builder parts are executed in
	 * the order from lowest to highest.
	 */
	int getOrder();

	/**
	 * Builds the related part of the given scenario.
	 * This method should just pick the data it needs from the passed {@link GivenScenario}
	 * to build the entities. If the objects in GivenScenario extend {@link EntityRefHolder},
	 * this method should set the created entity via `givenObject.setEntity()`, so the entity
	 * can be accessed afterwards in the test or by other builder parts.
	 *
	 * @param givenScenario The configured {@link GivenScenario}
	 */
	void build(G givenScenario);

	/**
	 * If you want to clean up the created entities after the test, you can implement
	 * this method. It will be called in the reversed order of the build() method.
	 *
	 * @param givenScenario The configured {@link GivenScenario}
	 */
	default void cleanup(G givenScenario) {

	}

}
