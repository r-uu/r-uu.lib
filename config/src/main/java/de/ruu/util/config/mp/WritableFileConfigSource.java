package de.ruu.util.config.mp;

import lombok.NonNull;
import org.eclipse.microprofile.config.spi.ConfigSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class WritableFileConfigSource implements ConfigSource
{
	private final Properties properties = new Properties();
	private       Path       configPath;

	public WritableFileConfigSource() { this(Paths.get("config/runtime.properties")); }

	public WritableFileConfigSource(@NonNull Path path)
	{
		this.configPath = path;
		try (InputStream in = Files.newInputStream(configPath)) { properties.load(in); }
		catch (IOException e) { } // file not found or cannot be read, will be created on first write
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

	@Override public Set<String> getPropertyNames() { return properties.stringPropertyNames(); }

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
