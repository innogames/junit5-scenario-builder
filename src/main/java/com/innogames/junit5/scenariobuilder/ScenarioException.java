package com.innogames.junit5.scenariobuilder;

/**
 * Exception that is thrown if something fails while building the scenario.
 */
public class ScenarioException extends RuntimeException {

	public ScenarioException(String message) {
		super(message);
	}

	public ScenarioException(String message, Throwable cause) {
		super(message, cause);
	}

}
