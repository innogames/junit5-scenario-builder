package com.innogames.scenariobuilder;

/**
 * Holds a reference of the related entity.
 *
 * <p>
 * Classes inside a {@link GivenScenario} can extend this class to be able to hold the
 * related entity that was created by the scenario builder in the background.
 * This entity can then be accessed by other {@link ScenarioBuilderPart}s or after the
 * scenario has been built.
 * </p>
 *
 * Example:
 * <pre>
 * var userRef = new Ref&lt;UserEntity&gt;();
 *
 * scenarioBuilder.build(scenario -&gt; scenario
 *     .withUser(user -&gt; user
 *         .ref(userRef)
 *     )
 * );
 *
 * UserEntity entity = userRef.get();
 * </pre>
 */
public abstract class EntityRefHolder<Given, Entity> {

	protected Ref<Entity> entityRef = new Ref<>();

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
