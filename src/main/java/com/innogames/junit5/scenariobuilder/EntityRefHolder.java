package com.innogames.junit5.scenariobuilder;

/**
 * Classes inside a {@link GivenScenario} can extend this class to be able to hold the
 * related entity that was created by the scenario builder in the background.
 * You can then access the entity later in the test or in other scenario builder parts.
 * <p>
 *
 * Example:
 * <pre>
 * var accountRef = new Ref&lt;AccountEntity>();
 *
 * scenarioBuilder.build(scenario -> scenario
 *     .withAccount(account -> account
 *         .ref(accountRef)
 *     )
 * );
 *
 * AccountEntity entity = accountRef.get();
 * </pre>
 */
public abstract class EntityRefHolder<Given, Entity> {

	private Ref<Entity> entityRef = new Ref<>();

	@SuppressWarnings("unchecked")
	public Given ref(Ref<Entity> entityRef) {
		this.entityRef = entityRef;
		return (Given) this;
	}

	public Entity getEntity() {
		var entity = entityRef.get();
		if (entity == null) {
			throw new ScenarioException("No Entity set in class " + getClass().getName());
		}
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entityRef.set(entity);
	}

}
