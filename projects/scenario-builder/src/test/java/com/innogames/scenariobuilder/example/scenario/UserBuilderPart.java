package com.innogames.scenariobuilder.example.scenario;

import com.innogames.scenariobuilder.ScenarioBuilderPart;
import com.innogames.scenariobuilder.example.domain.UserEntity;

import org.junit.platform.commons.util.StringUtils;

import java.util.UUID;

/**
 * Creates user entities from {@link GivenUser} objects in the given scenario.
 */
public class UserBuilderPart implements ScenarioBuilderPart<GivenAppScenario> {

	@Override
	public int getOrder() {
		return 1;
	}

	@Override
	public void build(GivenAppScenario givenScenario) {
		givenScenario.getUsers().forEach(givenUser -> {
			UserEntity user = new UserEntity();
			user.setId(UUID.randomUUID());

			if (StringUtils.isNotBlank(givenUser.getUsername())) {
				user.setUsername(givenUser.getUsername());
			}

			if (StringUtils.isNotBlank(givenUser.getPassword())) {
				user.setPassword(givenUser.getPassword());
			}

			// ... Usually the user should be stored to the database here

			// Store the created entity in the related "Given" object, so it can
			// be accessed later in the test or by other Builder Parts.
			givenUser.setEntity(user);
		});
	}

}