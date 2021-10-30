package com.innogames.junit5.scenariobuilder;

/**
 * Base interface for a configurable test scenario.
 *
 * <p>
 * Derived classed should contain information about the scenario you want to build inside a test.
 * It is recommended to create a fluent builder-like API which makes your scenario configuration
 * readable. A GivenScenario can contain sub-objects whose class names should be prefixed with
 * "Given" to make the intention clear.
 * </p>
 * <p>
 * As an example, if your application contains users, create a class called GivenUser that holds
 * configuration for one user. Here's an example how it could look like:
 * </p>
 *
 * <pre>
 *  public class GivenAppScenario implements GivenScenario {
 *
 *      private final List&lt;GivenUser&gt; users = new ArrayList&lt;&gt;();
 *
 *      public List&lt;GivenUser&gt; getUsers() {
 * 	        return users;
 *      }
 *
 * 	    public GivenAppScenario withUser(Consumer&lt;GivenUser&gt; userConsumer) {
 * 	        GivenUser givenUser = new GivenUser();
 * 	        userConsumer.accept(givenUser);
 * 	        users.add(givenUser);
 * 	        return this;
 *      }
 *
 * }
 * </pre>
 */
public interface GivenScenario {

}
