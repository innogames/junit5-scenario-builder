package com.innogames.scenariobuilder.junit5.examples.todo.scenario.builder;

import com.innogames.scenariobuilder.ScenarioBuilderPart;
import com.innogames.scenariobuilder.junit5.examples.todo.domain.TodoList;
import com.innogames.scenariobuilder.junit5.examples.todo.scenario.given.GivenTodoList;
import com.innogames.scenariobuilder.junit5.examples.todo.scenario.given.GivenTodoScenario;
import com.innogames.scenariobuilder.junit5.examples.todo.scenario.given.GivenUser;

import java.util.UUID;

/**
 * Creates TodoLists that are defined in the GivenScenario.
 */
public class TodoListBuilderPart implements ScenarioBuilderPart<GivenTodoScenario> {

	@Override
	public int getOrder() {
		return BuilderPartOrder.TODO_LISTS.ordinal();
	}

	@Override
	public void build(GivenTodoScenario givenScenario) {
		givenScenario.getUsers().forEach(givenUser ->
			givenUser.getTodoLists().forEach(givenTodoList ->
				createTodoList(givenUser, givenTodoList)));
	}

	private void createTodoList(GivenUser givenUser, GivenTodoList givenTodoList) {
		// Based on the defined BuilderPartOrder, the UserBuilderPart was executed before.
		// That means a user was already created and we can access the userId here.
		UUID userId = givenUser.getEntity().getId();

		var todoList = new TodoList();
		todoList.setId(UUID.randomUUID());
		todoList.setUserId(userId);
		todoList.setName(givenTodoList.getName());

		givenTodoList.setEntity(todoList);
	}

}