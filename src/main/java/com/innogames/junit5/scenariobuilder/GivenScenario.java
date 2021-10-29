package com.innogames.junit5.scenariobuilder;

/**
 * <p>
 * Base interface for a custom test scenario. Derived classes should hold
 * the configuration of a whole test scenario.
 * </p>
 *
 * <p>
 * All data in here is specific to your application. It is recommended to create fluent methods
 * to set the data in tests. A GivenScenario can contain sub-objects which should also be prefixed
 * with "Given" to make the intention clear. As an example, if your application contains accounts,
 * create a class called GivenAccount that holds configuration for one account.
 * Here's an example how it could look like:
 * </p>
 *
 * <pre>
 *  public class GivenAppScenario implements GivenScenario {
 *
 *      private final List&lt;GivenAccount&gt; accounts = new ArrayList&lt;&gt;();
 *
 *      public List&lt;GivenAccount&gt; getAccounts() {
 * 	        return accounts;
 *      }
 *
 * 	    public GivenAppScenario withAccount(Consumer&lt;GivenAccount&gt; accountConsumer) {
 * 	        GivenAccount givenAccount = new GivenAccount();
 * 	        accountConsumer.accept(givenAccount);
 * 	        accounts.add(givenAccount);
 * 	        return this;
 *      }
 *
 * }
 * </pre>
 */
public interface GivenScenario {

}
