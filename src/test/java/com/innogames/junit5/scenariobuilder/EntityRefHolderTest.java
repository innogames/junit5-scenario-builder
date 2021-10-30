package com.innogames.junit5.scenariobuilder;

import com.innogames.junit5.scenariobuilder.examples.gettingstarted.domain.UserEntity;
import com.innogames.junit5.scenariobuilder.examples.gettingstarted.scenario.GivenUser;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EntityRefHolderTest {

	@Test
	public void test_getEntity_throws_exception_if_not_set() {
		var givenUser = new GivenUser();
		assertThrows(ScenarioException.class, givenUser::getEntity);
	}

	@Test
	public void test_getEntity_from_entityRefHolder() {
		var givenUser = new GivenUser();

		var user = new UserEntity();
		user.setId(UUID.randomUUID());
		user.setUsername("Christian");
		givenUser.setEntity(user);

		assertSame(user, givenUser.getEntity());
	}

	@Test
	public void test_get_entity_from_ref() {
		var userRef = new Ref<UserEntity>();
		var givenUser = new GivenUser();
		givenUser.ref(userRef);

		var user = new UserEntity();
		user.setId(UUID.randomUUID());
		user.setUsername("Christian");
		givenUser.setEntity(user);

		assertSame(user, userRef.get());
	}

}