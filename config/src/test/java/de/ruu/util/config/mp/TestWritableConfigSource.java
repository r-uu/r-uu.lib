package de.ruu.util.config.mp;

import de.ruu.lib.cdi.common.CDIExtension;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.enterprise.inject.spi.CDI;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anEmptyMap;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@Slf4j
class TestWritableConfigSource
{
	@TempDir private Path tempDir;

	private Path                             configFilePath;
	private TestableWritableFileConfigSource configSource;

	@BeforeEach void beforeEach()
	{
		configFilePath = tempDir.resolve("runtime.properties");
		configSource   = new TestableWritableFileConfigSource(configFilePath);
	}

	@Test void testEmptyInitially()
	{
		assertThat(configSource.getProperties(), is(anEmptyMap()));
		assertThat(configSource.getValue("key"), is(nullValue()));
	}

	@Test void testSetAndGetValue() throws IOException
	{
		configSource.setValue("key", "value");
		assertThat("value", is(configSource.getValue("key")));

		Map<String, String> props = configSource.getProperties();
		assertThat(1, is(props.size()));
		assertThat("value", is(props.get("key")));
	}

	@Test void testOverwriteValue() throws IOException
	{
		configSource.setValue("key", "value1");
		configSource.setValue("key", "value2");

		assertThat("value2", is(configSource.getValue("key")));
	}

	@Test void testPersistence() throws IOException
	{
		configSource.setValue("username", "admin");

		// new instance simulates restart and should read from the same file
		WritableFileConfigSource newInstance = new TestableWritableFileConfigSource(configFilePath);
		assertThat("admin", is(newInstance.getValue("username")));
	}

	@Test void testConfigPropertyInjection() throws IOException
	{
		configSource.setValue("app.name"   , "TestApp");
		configSource.setValue("app.timeout", "60");

		InputStream beansXml = getClass().getClassLoader().getResourceAsStream("META-INF/beans.xml");
		log.debug("_beans.xml found: " + (beansXml != null));

		try
		{
			SeContainer seContainer =
					SeContainerInitializer
							.newInstance()
//							.disableDiscovery() // disable automatic discovery of beans
							                    // weld would otherwise fail with
							                    // "Invalid bean archive scanning result - found multiple results with
							                    //  the same reference:
							                    //  C:\Users\r-uu\develop\github\r-uu.lib\config\target\test-classes"
							.addExtensions(CDIExtension.class)
							.addBeanClasses(Configurable.class, WritableFileConfigSource.class)             // with discovery disabled, we need to add
//							.addBeanClasses(WritableFileConfigSource.class) // the bean class explicitly
							.initialize();

		}
		catch (Exception e)
		{
			log.error(
					"failure initialising seContainer\n" +
					"_MAKE SURE TO NOT HAVE A beans.xml IN src/test/resources/META-INF\n" +
					" WITH MANUAL CONFIGURATION OF SeContainerInitializer", e);
		}

//		System.setProperty("log4j.debug", "true");
//		System.setProperty("org.jboss.logging.provider", "slf4j");
//		System.setProperty("org.jboss.weld.environment.deployment.discovery.scan", "true");

		Configurable configurable = CDI.current().select(Configurable.class).get();

		assertThat("TestApp", is(configurable.appName()));
		assertThat(60       , is(configurable.timeout()));
	}

	// helper class with configurable path for testing
	static class TestableWritableFileConfigSource extends WritableFileConfigSource
	{
		private final Path path;

		public TestableWritableFileConfigSource(Path path)
		{
			super(path);
			this.path = path;
		}
	}

}