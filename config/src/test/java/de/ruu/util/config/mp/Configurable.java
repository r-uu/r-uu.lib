package de.ruu.util.config.mp;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * it is essential to have META-INF/beans.xml in src/test/resource if this class is in src/test/java and not to have
 * another beans.xml in src/main/resource, otherwise the test will fail with
 * <pre>
 * Caused by: java.lang.IllegalStateException: WELD-ENV-000033: Invalid bean archive scanning result - found multiple results with the same reference
 * </pre>
 */
@ApplicationScoped public class Configurable
{
	@ConfigProperty(name = "app.name", defaultValue = "DefaultApp")
	private String appName;

	@ConfigProperty(name = "app.timeout", defaultValue = "30")
	private int timeout;

	public String appName() {
		return appName;
	}

	public int timeout() {
		return timeout;
	}
}
