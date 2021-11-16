package com.innogames.scenariobuilder.junit5.examples.todo.domain;

import java.util.UUID;

/**
 * Example user entity that is meant to be stored into a database.
 */
public class User {

	private UUID id;
	private String username;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
