package com.innogames.scenariobuilder.examples.todo.scenario.builder;

import com.innogames.scenariobuilder.ScenarioBuilderPart;
import com.innogames.scenariobuilder.examples.todo.domain.Task;
import com.innogames.scenariobuilder.examples.todo.scenario.given.GivenTask;
import com.innogames.scenariobuilder.examples.todo.scenario.given.GivenTodoList;
import com.innogames.scenariobuilder.examples.todo.scenario.given.GivenTodoScenario;
import com.innogames.scenariobuilder.examples.todo.scenario.given.GivenUser;

import java.util.UUID;

/**
 * Creates tasks that are defined in the GivenScenario.
 */
public class TaskBuilderPart implements ScenarioBuilderPart<GivenTodoScenario> {

	@Override
	public int getOrder() {
		return BuilderPartOrder.TASKS.ordinal();
	}

	@Override
	public void build(GivenTodoScenario givenScenario) {
		givenScenario.getUsers().forEach(givenUser ->
			givenUser.getTodoLists().forEach(givenTodoList ->
				givenTodoList.getTodos().forEach(givenTask ->
					createTodo(givenUser, givenTodoList, givenTask))));
	}

	private void createTodo(GivenUser givenUser, GivenTodoList givenTodoList, GivenTask givenTask) {
		// Based on the defined BuilderPartOrder, the other builder parts were executed before.
		// So, we can safely access the entity references that were created by the other builder parts.
		UUID userId = givenUser.getEntity().getId();
		UUID todoListId = givenTodoList.getEntity().getId();

		var task = new Task();
		task.setId(UUID.randomUUID());
		task.setUserId(userId);
		task.setTodoListId(todoListId);
		task.setText(givenTask.getText());
		task.setComplete(givenTask.isComplete());

		givenTask.setEntity(task);
	}

}