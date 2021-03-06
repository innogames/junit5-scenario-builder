package com.innogames.scenariobuilder;

import com.innogames.scenariobuilder.example.domain.UserEntity;
import com.innogames.scenariobuilder.example.scenario.GivenUser;

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

	@Test
	public void test_ref_reuses_entity() {
		var oldUserRef = new Ref<UserEntity>();
		var givenUser = new GivenUser();
		givenUser.ref(oldUserRef);

		var user = new UserEntity();
		user.setId(UUID.randomUUID());
		user.setUsername("Christian");
		givenUser.setEntity(user);

		var newUserRef = new Ref<UserEntity>();
		givenUser.ref(newUserRef);
		assertSame(user, newUserRef.get());
	}

}