package com.innogames.scenariobuilder.junit5;

import com.innogames.scenariobuilder.GivenScenario;
import com.innogames.scenariobuilder.ScenarioBuilder;
import com.innogames.scenariobuilder.ScenarioBuilderPart;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.Collection;
import java.util.Optional;

/**
 * Base Junit 5 Extension that needs to be extended to add the Scenario Builder functionality to your tests.
 *
 * <p>
 * Note that you should not use this class directly in the @ExtendWith annotation. Instead, you
 * have to extend it to implement the necessary factory methods. For example:
 *
 * <pre>
 *  &#64;Component
 *  public class AppScenarioExtension extends ScenarioExtension&lt;GivenAppScenario&gt; {
 *
 *      &#64;Override
 *      protected Collection&lt;ScenarioBuilderPart&lt;GivenAppScenario&gt;&gt; getBuilderParts(ExtensionContext extensionContext) {
 *          // If you use Spring Framework and your builder parts are Spring Beans, you can load them here via
 *          // `SpringExtension.getApplicationContext(extensionContext).getBeansOfType(ScenarioBuilderPart.class)`
 *
 *          return List.of(
 *              new MyFirstBuilderPart(),
 *              new MySecondBuilderPart(),
 *              new MyThirdBuilderPart()
 *          );
 *      }
 *
 *      &#64;Override
 *      protected GivenAppScenario createGivenScenario(ExtensionContext extensionContext) {
 *          return new GivenAppScenario();
 *      }
 *
 *  }
 * </pre>
 *
 * Then add this extension via `@ExtendWith(AppScenarioExtension.class)` to your test class.
 * Now you can pass a ScenarioBuilder object as parameter to your test methods:
 *
 * <pre>
 *  &#64;Test
 *  public void someTest(ScenarioBuilder&lt;GivenAppScenario&gt; scenarioBuilder) {
 *      scenarioBuilder.build(...);
 *  }
 * </pre>
 *
 * See also {@link ScenarioAware} if you prefer a base test class.
 */
public abstract class ScenarioExtension<G extends GivenScenario> implements BeforeEachCallback, ParameterResolver, AfterEachCallback {

	private static final Namespace NAMESPACE = Namespace.create(ScenarioExtension.class);

	/**
	 * Returns all builder parts for the scenario builder.
	 * See {@link ScenarioBuilderPart} for more information.
	 *
	 * @param extensionContext Junit execution context for the current test
	 * @return All scenario builder parts
	 */
	protected abstract Collection<ScenarioBuilderPart<G>> getBuilderParts(ExtensionContext extensionContext);

	/**
	 * Creates a new instance of a {@link GivenScenario}.
	 * See description of {@link GivenScenario} for more information.
	 *
	 * @param extensionContext Junit execution context for the current test
	 * @return New instance of a {@link GivenScenario}
	 */
	protected abstract G createGivenScenario(ExtensionContext extensionContext);

	/**
	 * Creates an instance of the {@link ScenarioBuilder} that is used in the tests.
	 * <p>
	 * It is possible to extend the ScenarioBuilder class and override this method that should then return
	 * an instance of your custom class. This might also be useful in case you want to reduce the verbosity
	 * of your tests.
	 * For example, instead of doing this:
	 *
	 * <pre>
	 *  &#64;Test
	 *  public void someTest(ScenarioBuilder&lt;GivenAppScenario&gt; scenarioBuilder) {
	 *      scenarioBuilder.build(...);
	 *  }
	 * </pre>
	 *
	 * you can create a class AppScenario that extends ScenarioBuilder&lt;GivenAppScenario&gt; to write tests like this:
	 *
	 * <pre>
	 *  &#64;Test
	 *  public void someTest(AppScenario appScenario) {
	 *      appScenario.build(...);
	 *  }
	 * </pre>
	 *
	 * @param extensionContext Junit execution context for the current test
	 * @return New {@link ScenarioBuilder} instance
	 */
	protected ScenarioBuilder<G> createScenarioBuilder(ExtensionContext extensionContext) {
		return new ScenarioBuilder<>(() -> createGivenScenario(extensionContext), getBuilderParts(extensionContext));
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void beforeEach(ExtensionContext extensionContext) {
		extensionContext.getTestInstance().ifPresent(instance -> {
			if (instance instanceof ScenarioAware) {
				((ScenarioAware) instance).setScenarioBuilder(resolveScenarioBuilder(extensionContext));
			}
		});
	}

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
		return ScenarioBuilder.class.isAssignableFrom(parameterContext.getParameter().getType());
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
		return resolveScenarioBuilder(extensionContext);
	}

	@Override
	public void afterEach(ExtensionContext extensionContext) {
		getScenario(extensionContext).ifPresent(ScenarioBuilder::cleanup);
	}

	private ScenarioBuilder<G> resolveScenarioBuilder(ExtensionContext extensionContext) {
		ScenarioBuilder<G> scenarioBuilder = createScenarioBuilder(extensionContext);
		extensionContext.getStore(NAMESPACE).put(extensionContext.getUniqueId(), scenarioBuilder);
		return scenarioBuilder;
	}

	@SuppressWarnings({"unchecked"})
	private Optional<ScenarioBuilder<G>> getScenario(ExtensionContext extensionContext) {
		return Optional.ofNullable(extensionContext.getStore(NAMESPACE).get(extensionContext.getUniqueId(), ScenarioBuilder.class));
	}

}
