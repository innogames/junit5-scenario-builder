package com.innogames.junit5.scenariobuilder.examples.gettingstarted.scenario;

import com.innogames.junit5.scenariobuilder.EntityRefHolder;
import com.innogames.junit5.scenariobuilder.examples.gettingstarted.domain.UserEntity;

/**
 * Defines a given user in the {@link GivenAppScenario}.
 *
 * This class extends the {@link EntityRefHolder} in order to hold a reference to
 * the {@link UserEntity} that was created by the {@link UserBuilderPart}.
 */
public class GivenUser extends EntityRefHolder<GivenUser, UserEntity> {

	private String username = "testUser";
	private String password = "test";

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public GivenUser withUsername(String username) {
		this.username = username;
		return this;
	}

	public GivenUser withPassword(String password) {
		this.password = password;
		return this;
	}

}