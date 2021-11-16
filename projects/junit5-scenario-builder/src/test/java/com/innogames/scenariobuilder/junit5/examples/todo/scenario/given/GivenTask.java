package com.innogames.scenariobuilder.junit5.examples.todo.scenario.given;

import com.innogames.scenariobuilder.EntityRefHolder;
import com.innogames.scenariobuilder.junit5.examples.todo.domain.Task;

/**
 * Contains configuration for a task in the test scenario.
 */
public class GivenTask extends EntityRefHolder<GivenTask, Task> {

	private String text;
	private boolean complete;

	public String getText() {
		return text;
	}

	public boolean isComplete() {
		return complete;
	}

	public GivenTask withText(String text) {
		this.text = text;
		return this;
	}

	public GivenTask withComplete() {
		this.complete = true;
		return this;
	}

}