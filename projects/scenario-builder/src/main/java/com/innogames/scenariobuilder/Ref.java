package com.innogames.scenariobuilder;

import java.util.concurrent.atomic.AtomicReference;

/**
 * A simple object reference holder.
 * It is used in the scenario builder to reference entities that are not yet created.
 */
public class Ref<T> extends AtomicReference<T> {

	public Ref(T initialValue) {
		super(initialValue);
	}

	public Ref() {
	}

}
