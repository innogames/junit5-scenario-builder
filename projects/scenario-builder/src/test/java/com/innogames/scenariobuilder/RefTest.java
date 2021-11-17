package com.innogames.scenariobuilder;

import com.innogames.scenariobuilder.example.domain.UserEntity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class RefTest {

	@Test
	public void test_initialValue() {
		var entity = new UserEntity();
		var ref = new Ref<>(entity);
		assertSame(entity, ref.get());
	}

	@Test
	public void test_set() {
		var entity = new UserEntity();
		var ref = new Ref<>();
		ref.set(entity);
		assertSame(entity, ref.get());
	}

}