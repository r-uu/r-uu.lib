package de.ruu.lib.jpa.se.eclipselink;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import javax.sql.DataSource;

import org.eclipse.persistence.jpa.PersistenceProvider;
import org.junit.jupiter.api.Test;

import de.ruu.lib.jdbc.postgres.DataSourceFactory;
import de.ruu.lib.jdbc.postgres.JDBCURL;

class TestPersistenceUnitInfo
{
	@Test void test()
	{
		String host         = "localhost";
		int    port         = 5433;
		String databaseName = "modules";

		JDBCURL jdbcURL = new JDBCURL(host, port, databaseName);

		String user                = "modules";
		String password            = "modules";
		String persistenceUnitName = "modules";

		DataSourceFactory dataSourceFactory = new DataSourceFactory(jdbcURL, user, password);
		DataSource        dataSource        = dataSourceFactory.create();

		PersistenceUnitInfo persistenceUnitInfo =
				new PersistenceUnitInfo(persistenceUnitName, dataSource);

		assertThat(persistenceUnitInfo.getPersistenceUnitName(), is(persistenceUnitName));
		assertThat(persistenceUnitInfo.getPersistenceProvider(), is(PersistenceProvider.class));
	}
}