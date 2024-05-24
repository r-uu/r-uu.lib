package de.ruu.lib.jdbc.postgres;

import org.junit.jupiter.api.Test;
import org.postgresql.Driver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

class TestJDBCProperties
{
	@Test void test()
	{
		String user     = "modules";
		String password = "modules";

		JDBCProperties jdbcProperties =
				new JDBCProperties(
						new JDBCURL("localhost", 5433, "modules"), user, password);

		assertThat(jdbcProperties, is(not(nullValue())));

		assertThat(jdbcProperties.user(),     is(user));
		assertThat(jdbcProperties.password(), is(password));
		assertThat(jdbcProperties.driver(),   is(Driver.class.getName()));
	}
}