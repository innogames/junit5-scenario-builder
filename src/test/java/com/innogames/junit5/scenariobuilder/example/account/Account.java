package com.innogames.junit5.scenariobuilder.example.account;

import java.util.UUID;

/**
 * Test entity that represents an account
 */
public class Account {

	private UUID id;

	private String name;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
