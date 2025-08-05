package de.ruu.util.config.mp;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class WritableFileConfigSource implements ConfigSource
{
	private final Path       configPath = Paths.get("config/runtime.properties");
	private final Properties properties = new Properties();

	public WritableFileConfigSource()
	{
		try (InputStream in = Files.newInputStream(configPath))
		{
			properties.load(in);
		} catch (IOException e)
		{
			// file not found or cannot be read, will be created on first write
		}
	}

	@Override public Map<String, String> getProperties()
	{
		return
				properties
						.entrySet()
						.stream()
						.collect
						(
								Collectors.toMap
								(
										e -> e.getKey  ().toString(),
										e -> e.getValue().toString()
								)
						);
	}

	@Override public String getValue(String key) { return properties.getProperty(key); }

	@Override public String getName() { return getClass().getSimpleName(); }

	public void setValue(String key, String value) throws IOException
	{
		properties.setProperty(key, value);

		try (OutputStream out = Files.newOutputStream(configPath))
		{
			properties.store(out, "runtime overrides");
		}
	}

	@Override public int getOrdinal() { return 500; } // default ordinal, can be adjusted as needed
}
