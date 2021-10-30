package com.innogames.junit5.scenariobuilder.examples.todo.domain;

import java.util.UUID;

/**
 * Example TodoList entity that is meant to be stored in the database.
 */
public class TodoList {

	private UUID id;
	private UUID userId;
	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
