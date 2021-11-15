package com.innogames.scenariobuilder.examples.gettingstarted.domain;

import java.util.UUID;

/**
 * The User entity that is usually stored in the database.
 */
public class UserEntity {

	private UUID id;
	private String username;
	private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
