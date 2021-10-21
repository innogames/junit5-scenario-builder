package com.innogames.junit5.scenariobuilder.example.account;

import com.innogames.junit5.scenariobuilder.EntityRefHolder;
import com.innogames.junit5.scenariobuilder.example.GivenAppScenario;

/**
 * Defines a given account in the {@link GivenAppScenario}.
 *
 * This class extends the EntityRefHolder in order to hold a reference to
 * the Account that was created by the {@link AccountBuilder}.
 */
public class GivenAccount extends EntityRefHolder<GivenAccount, Account> {

	private String name;

	public String getName() {
		return name;
	}

	public GivenAccount withName(String name) {
		this.name = name;
		return this;
	}

}
