package de.ruu.lib.util.config.demo;

import de.ruu.lib.util.Files;
import de.ruu.lib.util.config.mp.WritableFileConfigSource;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Path;
import java.util.Optional;

import static de.ruu.lib.util.config.mp.ConfigSourceUtil.activeWritableFileConfigSource;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * JUnit 5 test for the WritableFileConfigSource.
 *
 * Demonstrates:
 * - CDI injection of @ConfigProperty
 * - Overriding default values from microprofile-config.properties
 * - Reading/writing config via WritableFileConfigSource
 * - Safe startup without bootstrap recursion
 */
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestWritableFileConfigSource
{
	private SeContainer container;
	private Path        testConfigPath;

	String testValueKey      = "test.key";
	String testValueInitial  = "test.value.initial";
	String testValueOverride = "test.value.new";

	@BeforeAll void beforeAll() throws Exception
	{
		// prepare a writable test config file path with initial content
		testConfigPath = Path.of("config", "application-test.properties");
		Files.writeToFile(testValueKey + "=" + testValueInitial, testConfigPath);

		// Let microprofile-config.properties (default source) point to our file
		// This can also be done via system property for testing
		System.setProperty(WritableFileConfigSource.CONFIG_FILE_NAME_KEY, testConfigPath.toString());

		// start CDI container
		SeContainerInitializer initializer = SeContainerInitializer.newInstance();
		container = initializer.initialize();
	}

	@AfterAll void afterAll() throws Exception
	{
		if (container != null) container.close();
		java.nio.file.Files.deleteIfExists(testConfigPath);
	}

	@Test void testWritableSourceInjectionAndOverride()
	{
		// get bean with injected config property
		MyBean bean = container.select(MyBean.class).get();

		// at first, the value comes from initialistion in beforeAll
		String initialValue = bean.value();
		log.debug("initial injected value: " + initialValue);
		assertThat(initialValue, is(testValueInitial));

		// retrieve the active WritableFileConfigSource
		Optional<WritableFileConfigSource> optional = activeWritableFileConfigSource();
		assertThat("WritableFileConfigSource should be active", optional.isPresent());

		// now, write a new value to the writable file
		WritableFileConfigSource writableSource = optional.get();
		writableSource.setProperty(testValueKey, testValueOverride);

		// retrieve value again via ConfigProperty (should be updated)
		String updatedValue = container.select(MyBean.class).get().value();
		assertThat(updatedValue, is(testValueOverride));
	}

	@Dependent // use @Dependent instead of @ApplicationScoped to force cdi to create a new instance for each injection
	           // for example via SeContainer.select(...)
	public static class MyBean
	{
		@Inject
		@ConfigProperty(name = "test.key", defaultValue = "default.from.annotation")
		private String value;

		public String value() { return value; }
	}
}