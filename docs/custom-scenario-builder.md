# Custom Scenario Builder

It is possible to create a custom `ScenarioBuilder` that is passed to all tests.

## Example

In this example we make use of the [Spring Framework](https://spring.io/).
Our goal is to **execute the builder in a database transaction**.

First, we create a custom class that extends `ScenarioBuilder` and
wraps the `build()` and `cleanup()` method in a transaction:

```java
public class AppScenario extends ScenarioBuilder<GivenAppScenario> {

    private final TransactionTemplate transactionTemplate;

    public AppScenario(GivenScenarioFactory<GivenAppScenario> givenScenarioFactory,
                       Collection<ScenarioBuilderPart<GivenAppScenario>> scenarioBuilderParts,
                       TransactionTemplate transactionTemplate) {
        super(givenScenarioFactory, scenarioBuilderParts);
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public void build(Consumer<GivenAppScenario> scenarioConsumer) {
        transactionTemplate.executeWithoutResult(status ->
            super.build(scenarioConsumer));
    }

    @Override
    public void cleanup() {
        transactionTemplate.executeWithoutResult(status ->
            super.cleanup());
    }
}
```

Then we override the `createScenarioBuilder()` method in our Extension class.

```java
public class AppScenarioExtension extends ScenarioExtension<GivenAppScenario> {

    @SuppressWarnings({"unchecked"})
    @Override
    protected Collection<ScenarioBuilderPart<GivenAppScenario>> getBuilderParts(ExtensionContext extensionContext) {
        ApplicationContext applicationContext = SpringExtension.getApplicationContext(extensionContext);
        
        // return all ScenarioBuilderParts that are known by Spring
        return applicationContext.getBeansOfType(ScenarioBuilderPart.class).values().stream()
            .map(part -> (ScenarioBuilderPart<GivenAppScenario>) part)
            .collect(Collectors.toList());
    }

    @Override
    protected GivenAppScenario createGivenScenario(ExtensionContext extensionContext) {
        return new GivenAppScenario();
    }

    @Override
    protected ScenarioBuilder<GivenAppScenario> createScenarioBuilder(ExtensionContext extensionContext) {
        ApplicationContext applicationContext = SpringExtension.getApplicationContext(extensionContext);
        TransactionTemplate transactionTemplate = applicationContext.getBean(TransactionTemplate.class);
        return new AppScenario(() -> createGivenScenario(extensionContext), getBuilderParts(extensionContext), transactionTemplate);
    }
}
```

Now, all tests execute the `build()` and `cleanup()` step in a transaction!

## Bonus

With a custom ScenarioBuilder we can now reference the new class in tests,
which also reduces the verbosity of the tests a bit.

Instead of

```java
@Test
public void test(ScenarioBuilder<GivenAppScenario> scenarioBuilder) {
    scenarioBuilder.build(...);
}
```

We can do this:

```java
@Test
public void test(AppScenario appScenario) {
    appScenario.build(...);
}
```
