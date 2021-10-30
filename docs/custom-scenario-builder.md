# Custom Scenario Builder

It is possible to create a custom `ScenarioBuilder` that is passed to all tests. For example if you want to reduce the
verbosity of tests, instead of

```java
@Test
public void test(ScenarioBuilder<GivenAppScenario> scenarioBuilder) {
    scenarioBuilder.build(...);
}
```

you can write it like this:

```java
@Test
public void test(AppScenario appScenario) {
    appScenario.build(...);
}
```

## Implementation

First, create your scenario builder that extends the original `ScenarioBuilder`:

```java
public class AppScenario extends ScenarioBuilder<GivenAppScenario> {

    public AppScenario(ExtensionContext extensionContext, Function<ExtensionContext, GivenAppScenario> givenScenarioFactory,
                       Collection<ScenarioBuilderPart<GivenAppScenario>> scenarioBuilderParts) {
        super(extensionContext, givenScenarioFactory, scenarioBuilderParts);
    }

}
```

Then you have to override the `createScenarioBuilder()` method in your Extension class:

```java
public class AppScenarioExtension extends ScenarioExtension<GivenAppScenario> {

    @Override
    protected Collection<ScenarioBuilderPart<GivenAppScenario>> getBuilderParts(ExtensionContext extensionContext) {
        return List.of(
            // ... your builder parts
        );
    }

    @Override
    protected GivenAppScenario createGivenScenario(ExtensionContext extensionContext) {
        return new GivenAppScenario();
    }

    @Override
    protected ScenarioBuilder<GivenAppScenario> createScenarioBuilder(ExtensionContext extensionContext) {
        // Create an instance of your custom Scenario Builder here
        return new AppScenario(extensionContext, this::createGivenScenario, getBuilderParts(extensionContext));
    }

}
```
