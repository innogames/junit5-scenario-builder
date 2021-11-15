package com.innogames.scenariobuilder.junit5;

import com.innogames.scenariobuilder.GivenScenario;
import com.innogames.scenariobuilder.ScenarioBuilder;

/**
 * You can add this interface to a base test class to avoid passing a ScenarioBuilder
 * to each test method.
 *
 * <p>
 * So, instead of this:
 * <pre>
 *  &#64;Test
 *  public void someTest(ScenarioBuilder&lt;GivenAppScenario&gt; scenarioBuilder) {
 *      scenarioBuilder.build(...);
 *  }
 * </pre>
 *
 * You can simplify it like this:
 * <pre>
 *  &#64;Test
 *  public void someTest() {
 *      buildScenario(...);
 *  }
 * </pre>
 *
 * This is how the base test class could look like:
 * <pre>
 * class BaseTest implements ScenarioAware&lt;GivenAppScenario&gt; {
 *
 *     private ScenarioBuilder&lt;GivenAppScenario&gt; scenarioBuilder;
 *
 *     &#64;Override
 *     public void setScenarioBuilder(ScenarioBuilder&lt;GivenAppScenario&gt; scenarioBuilder) {
 *         this.scenarioBuilder = scenarioBuilder;
 *     }
 *
 *     protected void buildScenario(Consumer&lt;GivenAppScenario&gt; scenarioConsumer) {
 *         scenarioBuilder.build(scenarioConsumer);
 *     }
 * }
 * </pre>
 */
public interface ScenarioAware<G extends GivenScenario> {

	void setScenarioBuilder(ScenarioBuilder<G> builder);

}
