package com.innogames.junit5.scenariobuilder;

import com.innogames.junit5.scenariobuilder.example.account.Account;
import com.innogames.junit5.scenariobuilder.example.account.GivenAccount;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EntityRefHolderTest {

	@Test
	public void test_getEntity_throws_exception_if_not_set() {
		var givenAccount = new GivenAccount();
		assertThrows(ScenarioException.class, givenAccount::getEntity);
	}

	@Test
	public void test_getEntity_from_entityRefHolder() {
		var givenAccount = new GivenAccount();

		var account = new Account();
		account.setId(UUID.randomUUID());
		account.setName("Christian");
		givenAccount.setEntity(account);

		assertSame(account, givenAccount.getEntity());
	}

	@Test
	public void test_get_entity_from_ref() {
		var accountRef = new Ref<Account>();
		var givenAccount = new GivenAccount();
		givenAccount.ref(accountRef);

		var account = new Account();
		account.setId(UUID.randomUUID());
		account.setName("Christian");
		givenAccount.setEntity(account);

		assertSame(account, accountRef.get());
	}

}