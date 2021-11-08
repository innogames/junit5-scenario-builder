# Entity References

The Scenario Builder creates entities "in the background" which means you can not access them directly in the test. But
with **Reference holder objects** this is no problem at all.

## The Ref class

This library provides a [Ref][RefClass] class that you can use in tests:

```java
@Test
public void example() {
    // Create a Ref that can hold a UserEntity.
    // For now, the userRef is just an "empty" object.
    var userRef = new Ref<UserEntity>();

    scenarioBuilder.build(scenario -> scenario
        .withUser(user -> user
            // Tell the Scenario Builder that it should store
            // the created user entity in the userRef object. 
            .ref(userRef)
            .withUsername("Christian")
        )
    );

    // After `scenarioBuilder.build()` was called, the reference
    // object is filled and you can access the UserEntity:
    assertNotNull(userRef.get().getId());
    assertEquals("Christian", userRef.get().getUsername());
}
```

There are two requirements you have to fulfill to make it work:

### 1. Extend EntityRefHolder

"Given" classes (that are used to configure a scenario) must extend from [EntityRefHolder][EntityRefHolder]. The
EntityRefHolder provides the `.ref()` method that you call to tell the Scenario Builder that it should store the entity
in the reference object.

See the [GivenUser][GivenUser] class in this example that contains a reference to the UserEntity:

```java
public class GivenUser extends EntityRefHolder<GivenUser, UserEntity> {
    // ...
}
```

### 2. Store entity to the Ref object

In the specific "Scenario Builder Parts" where you create the entities, you have to store the references. In the example
above the [UserBuilderPart][UserBuilderPart].build() method would look like this:

```java
@Override
public void build(GivenAppScenario givenScenario) {
    givenScenario.getUsers().forEach(givenUser -> {
        // Note that givenUser extends from EntityRefHolder
        
        UserEntity user = new UserEntity();
        user.setId(UUID.randomUUID());
        user.setUsername(givenUser.getUsername());
        user.setPassword(givenUser.getPassword());

        // ...Save user entity to the database

        // Store the created entity in the related "Given" object
        givenUser.setEntity(user);
    });
}
```

## Access entities across builder parts.

References can not only be accessed in the tests, but also in other Scenario Builder Parts. Let's take a To-Do App as
example. This app consists of:

- Users
- Todo Lists (one or more per User)
- Tasks inside a Todo List

Building a test scenario could look like this:

```java
scenarioBuilder.build(scenario -> scenario
    .withUser(user -> user
        .withTodoList("Shopping List", list -> list
            .withTodo("Milk")
            .withTodo("Coffee")
        )
        .withTodoList("Publish Library", list -> list
            .withTodo(todo -> todo
                .withText("Create repository")
                .withComplete()
            )
            .withTodo("Create Documentation")
            .withTodo("Publish to Maven Central")
        )
    )
);
```

Here we have 3 builder parts that have to be executed in the correct order:

1. [UserBuilderPart][UserBuilderPart]: Creates users.
2. [TodoListBuilderPart][TodoListBuilderPart]: Creates todo lists. A todo list is stored per user, so it has to access
   the User entity that was created by the UserBuilderPart to get the generated userId.
3. [TaskBuilderPart][TaskBuilderPart]: Creates tasks inside a todo list. It accesses the User and TodoList entities that
   were crated by the other builder parts in order to get the generated IDs.

> More information about ordering [here](./ordering.md).

Please have a look at the [full implementation of this example here][TodoExample].


[RefClass]: ../src/main/java/com/innogames/junit5/scenariobuilder/Ref.java

[EntityRefHolder]: ../src/main/java/com/innogames/junit5/scenariobuilder/EntityRefHolder.java

[GivenUser]: ../src/test/java/com/innogames/junit5/scenariobuilder/examples/gettingstarted/scenario/GivenUser.java

[UserBuilderPart]: ../src/test/java/com/innogames/junit5/scenariobuilder/examples/gettingstarted/scenario/UserBuilderPart.java

[TodoExample]: ../src/test/java/com/innogames/junit5/scenariobuilder/examples/todo

[UserBuilderPart]: ../src/test/java/com/innogames/junit5/scenariobuilder/examples/todo/scenario/builder/UserBuilderPart.java

[TodoListBuilderPart]: ../src/test/java/com/innogames/junit5/scenariobuilder/examples/todo/scenario/builder/TodoListBuilderPart.java

[TaskBuilderPart]: ../src/test/java/com/innogames/junit5/scenariobuilder/examples/todo/scenario/builder/TaskBuilderPart.java