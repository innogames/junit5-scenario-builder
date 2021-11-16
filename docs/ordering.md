# Builder Part ordering

The order in which builder parts are executed is very important. Looking at [this TodoList example][TodoExample] we have
3 builder parts:

1. [UserBuilderPart][UserBuilderPart] that creates users
2. [TodoListBuilderPart][TodoListBuilderPart] that creates todo lists
3. [TaskBuilderPart][TaskBuilderPart] that creates tasks inside a todo list

A good idea is to create an `enum` for an easy order management:

```java
public enum BuilderPartOrder {

    USERS,
    TODO_LISTS,
    TASKS

}
```

The enum defines from top to bottom in which order the builder parts are executed. In each builder part class, you can
then just define the position in the enum declaration as order:

```java
public class UserBuilderPart implements ScenarioBuilderPart<GivenTodoScenario> {

    @Override
    public int getOrder() {
        return BuilderPartOrder.USERS.ordinal();
    }

    // ...
}
```

[TodoExample]: ../projects/junit5-scenario-builder/src/test/java/com/innogames/scenariobuilder/junit5/examples/todo

[UserBuilderPart]: ../projects/junit5-scenario-builder/src/test/java/com/innogames/scenariobuilder/junit5/examples/todo/scenario/builder/UserBuilderPart.java

[TodoListBuilderPart]: ../projects/junit5-scenario-builder/src/test/java/com/innogames/scenariobuilder/junit5/examples/todo/scenario/builder/TodoListBuilderPart.java

[TaskBuilderPart]: ../projects/junit5-scenario-builder/src/test/java/com/innogames/scenariobuilder/junit5/examples/todo/scenario/builder/TaskBuilderPart.java
