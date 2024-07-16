package de.ruu.lib.jpa.se.hibernate.postgres;

import de.ruu.lib.jdbc.core.JDBCURL;
import de.ruu.lib.jdbc.postgres.DataSourceFactory;
import de.ruu.lib.jpa.se.hibernate.EntityManagerFactoryProducer;
import de.ruu.lib.jpa.se.hibernate.PersistenceUnitInfo;
import de.ruu.lib.jpa.se.hibernate.PersistenceUnitProperties;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.sql.DataSource;
import java.util.List;

import static de.ruu.lib.jpa.se.hibernate.PersistenceUnitProperties.HBM2DLLAuto.CREATE;
import static java.util.Objects.isNull;

@Slf4j
public abstract class AbstractEntityManagerProducer
{
	private EntityManager entityManager;

	@Inject
	@ConfigProperty(name = "de.ruu.lib.jpa.se.hibernate.postgres.AbstractEntityManagerProducer.dbhost", defaultValue = "localhost")
	private String host;

	@Inject
	@ConfigProperty(name = "de.ruu.lib.jpa.se.hibernate.postgres.AbstractEntityManagerProducer.dbport", defaultValue = "5432")
	private Integer port;

	@Inject
	@ConfigProperty(name = "de.ruu.lib.jpa.se.hibernate.postgres.AbstractEntityManagerProducer.dbname", defaultValue = "demo_test")
	private String dbName;

	@Inject
	@ConfigProperty(name = "de.ruu.lib.jpa.se.hibernate.postgres.AbstractEntityManagerProducer.dbuser", defaultValue = "demo_test")
	private String username;

	@Inject
	@ConfigProperty(name = "de.ruu.lib.jpa.se.hibernate.postgres.AbstractEntityManagerProducer.dbpass", defaultValue = "demo_test")
	private String password;

	@Inject
	@ConfigProperty(name = "de.ruu.lib.jpa.se.hibernate.postgres.AbstractEntityManagerProducer.puname", defaultValue = "demo_test")
	private String puname;

	/** call this method from method annotated with {@link jakarta.enterprise.inject.Produces} in subclasses */
	protected EntityManager produce()
	{
		if (!isNull(entityManager)) return entityManager;

		JDBCURL jdbcURL = new de.ruu.lib.jdbc.postgres.JDBCURL(host, port, dbName);

		DataSourceFactory dataSourceFactory = new DataSourceFactory(jdbcURL, username, password);
		DataSource        dataSource        = dataSourceFactory.create();

		PersistenceUnitInfo persistenceUnitInfo = new PersistenceUnitInfo(puname, dataSource);

		// customise managed classes
		managedClasses().forEach(persistenceUnitInfo::addManagedClass);

		PersistenceUnitProperties hibernateProperties =
				PersistenceUnitProperties
						.builder()
//								.dialect(PostgreSQLDialect.class)
								.formatSQL(true)
								.hbm2ddlAuto(CREATE)
								.jdbcDriver(org.postgresql.Driver.class)
								.jdbcURL(jdbcURL.asString())
								.showSQL(true)
						.build();

		EntityManagerFactoryProducer factoryProducer = new EntityManagerFactoryProducer(persistenceUnitInfo, hibernateProperties);

		EntityManagerFactory entityManagerFactory = factoryProducer.produce(username, password);

		entityManager = entityManagerFactory.createEntityManager();
		log.debug("created entity manager: {}", entityManager);

		return entityManager;
	}

	public abstract List<Class<?>> managedClasses();
}