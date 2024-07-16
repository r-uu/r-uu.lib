package de.ruu.lib.jdbc.postgres;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

import org.eclipse.microprofile.config.ConfigProvider;
import org.junit.jupiter.api.Test;

class TestJDBCURL
{
	@Test void test()
	{
		String databaseHost =
				ConfigProvider
						.getConfig()
						.getOptionalValue("database.host", String .class)
						.orElse("localhost");
		int    databasePort =
				ConfigProvider
						.getConfig()
						.getOptionalValue("database.port", Integer.class)
						.orElse(5433);
		String databaseName =
				ConfigProvider
						.getConfig()
						.getOptionalValue("database.port", String.class)
						.orElse("test");;

		JDBCURL jdbcURL = new JDBCURL(databaseHost, databasePort, databaseName);

		assertThat(jdbcURL, is(not(nullValue())));

		assertThat(jdbcURL.host(),         is(databaseHost));
		assertThat(jdbcURL.port(),         is(databasePort));
		assertThat(jdbcURL.databaseName(), is(databaseName));

		assertThat(jdbcURL.asString(), is(JDBCURL.PROTOCOL + "://" + databaseHost + ":" + databasePort + "/" + databaseName));
	}
}