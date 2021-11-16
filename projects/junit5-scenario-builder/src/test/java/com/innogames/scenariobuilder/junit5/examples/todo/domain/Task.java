package com.innogames.scenariobuilder.junit5.examples.todo.domain;

import java.util.UUID;

/**
 * Example task entity that is meant to be stored into a database.
 */
public class Task {

	private UUID id;
	private UUID userId;
	private UUID todoListId;
	private String text;
	private boolean complete;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public UUID getTodoListId() {
		return todoListId;
	}

	public void setTodoListId(UUID todoListId) {
		this.todoListId = todoListId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

}
