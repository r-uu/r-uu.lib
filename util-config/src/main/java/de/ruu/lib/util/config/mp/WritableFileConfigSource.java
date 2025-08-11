package de.ruu.lib.util.config.mp;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.spi.ConfigSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static de.ruu.lib.util.BooleanFunctions.not;

/**
 * Writable MicroProfile ConfigSource backed by a file.
 *
 * This implementation:
 * - Defers (lazy) initialization until first property access to avoid bootstrap recursion.
 * - Reads its file path from the standard MicroProfile config (config.file.name key).
 * - Falls back to a default path if no property is set.
 * - Supports saving and removing properties.
 *
 * Expected setup:
 *   1. Put "config.file.name=..." into microprofile-config.properties or other default source.
 *   2. This ConfigSource will use that value when it first loads.
 */
@Slf4j
public class WritableFileConfigSource implements ConfigSource
{
	public static final String CONFIG_FILE_NAME_KEY           = "config.file.name";
	public static final String CONFIG_FILE_NAME_VALUE_DEFAULT = "config/application.properties";

	private final Map<String, String> properties = new ConcurrentHashMap<>();

	private File    configFile;     // the file to read/write properties from/to
	private boolean loaded = false; // true if properties were loaded from config file

	/**
	 * Ensures that the file path is resolved and the file content is loaded.
	 * This is called lazily on first property access to avoid recursion during microprofile-config bootstrap.
	 */
	private synchronized void ensureLoaded()
	{
		if (loaded) return;

		// try to retrieve config file name from system property

		String configFileName = System.getProperty(CONFIG_FILE_NAME_KEY);

		if (configFileName == null || configFileName.isBlank())
		{
			// try to retrieve config file name from bootstrap properties
			String     bootstrapFileName   = "META-INF/microprofile-config.properties";
			Properties bootstrapProperties = new Properties();

			try (InputStream is = getClass().getClassLoader().getResourceAsStream(bootstrapFileName))
			{
				if (is != null) bootstrapProperties.load(new InputStreamReader(is, StandardCharsets.UTF_8));
			}
			catch (IOException e) { throw new UncheckedIOException("failure reading bootstrap config: " + bootstrapFileName, e); }

			// try to retrieve config file name from bootstrap properties or fall back to default
			configFileName = bootstrapProperties.getProperty(CONFIG_FILE_NAME_KEY, CONFIG_FILE_NAME_VALUE_DEFAULT);
		}

		// _important_ do not use ConfigProvider here to avoid bootstrap recursion
//		// get file name from already-initialized MP config sources
//		configFileName =
//				ConfigProvider
//						.getConfig()
//						.getOptionalValue("config.file.name", String.class)
//						.orElse("config/application.properties");

		configFile = new File(configFileName);
		load();
		loaded = true;
	}

	/**
	 * loads properties from {@link #configFile} into {@link #properties}, does nothing but print a warning if {@link
	 * #configFile} does not exist
	 */
	private void load() throws UncheckedIOException
	{
		if (not(configFile.exists()))
		{
			log.warn("config file not found: {}", configFile.getAbsolutePath());
			return;
		}

		try (InputStream is = new FileInputStream(configFile))
		{
			Properties props = new Properties();
			props.load(is);
			props.stringPropertyNames().forEach(key -> properties.put(key, props.getProperty(key)));
		}
		catch (IOException e) { throw new UncheckedIOException(e); }
	}

	/** saves the current {@link #properties} to the backing file {@link #configFile} */
	public void save() throws UncheckedIOException
	{
		if (configFile == null)
				throw new IllegalStateException("config file path is not set yet, access a property first to initialize.");

		try (OutputStream os = new FileOutputStream(configFile))
		{
			Properties props = new Properties();
			props.putAll(properties);
			props.store(os, "Writable config");
		}
		catch (IOException e) { throw new UncheckedIOException(e); }
	}

	public void setProperty(String key, String value)
	{
		ensureLoaded();
		properties.put(key, value);
		save();
	}

	public void removeProperty(String key)
	{
		ensureLoaded();
		properties.remove(key);
		save();
	}

	@Override public Map<String, String> getProperties()
	{
		ensureLoaded();
		return Collections.unmodifiableMap(properties);
	}

	@Override public Set<String> getPropertyNames()
	{
		ensureLoaded();
		return properties.keySet();
	}

	@Override public String getValue(String key)
	{
		ensureLoaded();
		return properties.get(key);
	}

	@Override public String getName() { return getClass().getSimpleName() + "(" + configFile.getAbsolutePath() + ")"; }

	@Override public int getOrdinal()
			{ return 500; } // higher than default microprofile-config files so it overrides them if keys overlap
}