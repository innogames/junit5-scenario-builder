![release](https://badgen.net/github/release/innogames/junit5-scenario-builder/stable?color=cyan) ![license](https://badgen.net/github/license/innogames/junit5-scenario-builder) ![publish](https://github.com/innogames/junit5-scenario-builder/actions/workflows/publish.yml/badge.svg)

# Scenario Builder for Integration Tests

A Junit 5 extension that introduces a "Scenario Builder" to your tests giving you the following benefits:

- Test scenarios are very easy to define, using a builder-like fluent API
- Tests are more readable and easy to understand
- Reduces the general maintenance effort

## Documentation

- [Getting started](./docs/getting-started.md)
- [Entity References](./docs/entity-references.md)
- [Builder Part ordering](./docs/ordering.md)
- [Cleanup after test execution](./docs/cleanup.md)
- [Custom Scenario Builder](./docs/custom-scenario-builder.md)

## Quick Example

Here's a simple example how a "login" test could look like:

```java
@Test
public void testLogin() {
    // Prepare test scenario by creating a user
    scenarioBuilder.build(scenario -> scenario
        .withUser(user -> user
            .withUsername("Christian")
            .withPassword("MySecretPassword")
        )
    );
    
    // ... Call login endpoint
    
    // ... Assert that login was successful
}
```

See more examples in the
[test folder.](./projects/junit5-scenario-builder/src/test/java/com/innogames/scenariobuilder/junit5/examples)
