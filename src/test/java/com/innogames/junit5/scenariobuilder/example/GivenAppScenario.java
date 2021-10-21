package com.innogames.junit5.scenariobuilder.example;

import com.innogames.junit5.scenariobuilder.GivenScenario;
import com.innogames.junit5.scenariobuilder.example.account.GivenAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Defines a given scenario for tests.
 */
public class GivenAppScenario implements GivenScenario {

	private final List<GivenAccount> accounts = new ArrayList<>();

	public List<GivenAccount> getAccounts() {
		return accounts;
	}

	public GivenAppScenario withAccount(Consumer<GivenAccount> accountConsumer) {
		var givenAccount = new GivenAccount();
		accountConsumer.accept(givenAccount);
		this.accounts.add(givenAccount);
		return this;
	}

}
