# Getting started

## 1. Add dependency

Using Maven:

```xml
<dependency>
    <groupId>com.innogames</groupId>
    <artifactId>junit5-scenario-builder</artifactId>
    <version>0.3.0</version>
    <scope>test</scope>
</dependency>
```

Using Gradle:

```groovy
testImplementation "com.innogames:junit5-scenario-builder:0.3.0"
```

## 2. Implement GivenScenario

The `GivenScenario` holds all information about the scenario that you want to build. The idea is that you just have to
configure this object in tests. The actual creation of entities happens in the background.

Everything inside this class is specific to your application. That means you have to create your own class that
implements the `GivenScenario` interface.

Here's an example implementation:

```java
// The "root" scenario that can be configured in tests.
// It must implement the GivenScenario interface 
public class GivenAppScenario implements GivenScenario {

    // In this scenario, you can configure users that should be
    // created by the Scenario Builder
    private final List<GivenUser> users = new ArrayList<>();

    public List<GivenUser> getUsers() {
        return users;
    }

    // You should provide a fluent API to increase the
    // readability of your tests!
    public GivenAppScenario withUser(Consumer<GivenUser> userConsumer) {
        var givenUser = new GivenUser();
        userConsumer.accept(givenUser);
        this.users.add(givenUser);
        return this;
    }

}

// It is recommended to use the "Given"-prefix on all classes that
// are used inside the GivenScenario to make the intention clear.
public class GivenUser {

    // You should set default values that are used when these
    // fields are not configured in tests
    private String username = "testUser";
    private String password = "test";

    // ... getters for username and password here

    public GivenUser withUsername(String username) {
        this.username = username;
        return this;
    }

    public GivenUser withPassword(String password) {
        this.password = password;
        return this;
    }

}

``` 

## 3. Implement Scenario Builder Parts

While "Given" objects only hold the scenario configuration, "Builder Parts" are there to actually create all entities.
The GivenScenario example above contains users, so you have to define a `ScenarioBuilderPart` that creates those users.

```java
public class UserBuilderPart implements ScenarioBuilderPart<GivenAppScenario> {

    @Override
    public int getOrder() {
        // Builder parts are executed in the order that is defined
        // here (from lowest to highest).
        return 1;
    }

    @Override
    public void build(GivenAppScenario givenScenario) {
        // This method receives the whole GivenScenario object, but you should
        // just pick the information that is needed for this specific builder part.
    
        givenScenario.getUsers().forEach(givenUser -> {
            // ... create User entitiy here and store it in the database.
        });
    }

}
```

## 4. Create Extension

Lastly, you have to create the Extension class for Junit. Here you define the available Builder Parts and tell the
Scenario Builder how to create your `GivenScenario` object.

```java
public class AppScenarioExtension extends ScenarioExtension<GivenAppScenario> {

    @Override
    protected Collection<ScenarioBuilderPart<GivenAppScenario>> getBuilderParts(ExtensionContext extensionContext) {
        // Returns all builder parts.
        // If you use Spring Framework and your builder parts are Spring Beans, you can load them here via
        // `SpringExtension.getApplicationContext(extensionContext).getBeansOfType(ScenarioBuilderPart.class)`
        return List.of(
            new UserBuilderPart()
        );
    }

    @Override
    protected GivenAppScenario createGivenScenario(ExtensionContext extensionContext) {
        // Here you just create a new instance of the GivenScenario class.
        // This method is called per test execution and you can get information about
        // the running test from the passed extensionContext if needed.
        return new GivenAppScenario();
    }

}
```

## 5. Use Extension in test

Finally, you can add the extension via `@ExtendWith` to your tests. You have two options to access the Scenario Builder:

### Option 1: Pass Scenario Builder as method parameter

If you add a parameter of type `ScenarioBuilder` to your test methods, it will be injected automatically.

```java
@ExtendWith(AppScenarioExtension.class)
class LoginIntegrationTest {

    @Test
    public void testLogin(ScenarioBuilder<GivenAppScenario> scenarioBuilder) {
        scenarioBuilder.build(scenario -> scenario
            .withUser(user -> user
                .withUsername("Christian")
                .withPassword("MySecretPassword")
            )
        );

        // Usually you would call a login endpoint here with username and password
        // and assert that the login was successful.
    }

}
```

> See also [Custom Scenario Builder](./custom-scenario-builder.md) if you want to reduce the verbosity a bit.

### Option 2: Implement ScenarioAware interface

If you have a **base test class**, you can implement the `ScenarioAware` interface to inject the ScenarioBuilder instead
of passing it as parameter in each test.

```java
class UserIntegrationTest extends BaseIntegrationTest {

    @Test
    public void testLogin() {
        scenarioBuilder.build(scenario -> scenario
            .withUser(user -> user
                .withUsername("Christian")
                .withPassword("MySecretPassword")
            )
        );

        // Usually you would call a login endpoint here with username and password
        // and assert that the login was successful.
    }

}


@ExtendWith(AppScenarioExtension.class)
public abstract class BaseIntegrationTest implements ScenarioAware<GivenAppScenario> {

    protected ScenarioBuilder<GivenAppScenario> scenarioBuilder;

    @Override
    public void setScenarioBuilder(ScenarioBuilder<GivenAppScenario> builder) {
        scenarioBuilder = builder;
    }

}
```

## How to access the created entities in tests

It is very common that you need the entities or IDs that were generated by the Scenario Builder later in the test. For
example if you want to test a user profile endpoint and this endpoints requires a userId:

```java
@Test
public void testProfile() {
    // A reference holder object which is empty at this point
    var userRef = new Ref<UserEntity>();

    scenarioBuilder.build(scenario -> scenario
        .withUser(user -> user
            // Tell the Scenario Builder that it should store
            // the created user entity in the reference object 
            .ref(userRef)
            .withUsername("Christian")
        )
    );

    // To call an endpoint like "/profile/<userId>" you can now get
    // the generated userId via `userRef.get().getId()`

    assertNotNull(userRef.get().getId());
}
```

To accomplish this:

- The `GivenUser` class must extend from `EntityRefHolder`.
- The `UserBuilderPart` must store the entity in the `GivenUser` object after creation.

Read more about it in the [Entity References](./entity-references.md) section or look at the
[example implementation in the test folder.](../projects/junit5-scenario-builder/src/test/java/com/innogames/scenariobuilder/junit5/examples/gettingstarted)
