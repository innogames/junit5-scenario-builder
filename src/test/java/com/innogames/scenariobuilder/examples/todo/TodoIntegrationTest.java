package com.innogames.scenariobuilder.examples.todo;

import com.innogames.scenariobuilder.Ref;
import com.innogames.scenariobuilder.examples.todo.domain.Task;
import com.innogames.scenariobuilder.examples.todo.domain.User;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoIntegrationTest extends BaseIntegrationTest {

	@Test
	public void testTodoExample() {
		var userRef = new Ref<User>();
		var todoRef = new Ref<Task>();

		scenarioBuilder.build(scenario -> scenario
			.withUser(user -> user
				.ref(userRef)
				.withTodoList("Shopping List", list -> list
					.withTodo("Milk")
					.withTodo("Coffee")
				)
				.withTodoList("Publish Library", list -> list
					.withTodo(todo -> todo
						.ref(todoRef)
						.withText("Create repository")
						.withComplete()
					)
					.withTodo("Create Documentation")
					.withTodo("Publish to Maven Central")
				)
			)
		);

		Task task = todoRef.get();
		assertEquals("Create repository", task.getText());
		assertEquals(userRef.get().getId(), task.getUserId());
	}

}
