package com.innogames.junit5.scenariobuilder.example.account;

import com.innogames.junit5.scenariobuilder.ScenarioBuilderPart;
import com.innogames.junit5.scenariobuilder.example.GivenAppScenario;

import java.util.UUID;

/**
 * This builder part creates an Account entity
 */
public class AccountBuilder implements ScenarioBuilderPart<GivenAppScenario> {

	@Override
	public int getOrder() {
		return 1;
	}

	@Override
	public void build(GivenAppScenario givenScenario) {
		givenScenario.getAccounts().forEach(givenAccount -> {
			var account = new Account();
			account.setId(UUID.randomUUID());
			account.setName(givenAccount.getName());

			// usually we would save this account to the database here

			givenAccount.setEntity(account);
		});
	}

}
