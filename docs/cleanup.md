# Cleanup after test execution

If you want to clean up the created entities after the test, you can override the `cleanup()` method in your Scenario
Builder parts.

All cleanup methods are called in the reversed order of the `build()` method.

```java
public class UserBuilderPart implements ScenarioBuilderPart<GivenTodoScenario> {

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public void build(GivenTodoScenario givenScenario) {
        // create user entites
    }

    @Override
    public void cleanup(GivenAppScenario givenScenario) {
        givenScenario.getUsers().stream()
            .map(GivenUser::getEntity)
            .forEach(userEntity -> {
                // delete userEntity 
            });
    }

}
```
