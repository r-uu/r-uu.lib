package de.ruu.lib.jpa.se.hibernate;

import de.ruu.lib.jpa.core.AbstractPersistenceUnitInfo;
import javax.sql.DataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;

/** persistence unit info with hibernate as persistence provider */
public class PersistenceUnitInfo extends AbstractPersistenceUnitInfo
{
	public PersistenceUnitInfo(String persistenceUnitName, DataSource dataSource)
	{
		super(persistenceUnitName, HibernatePersistenceProvider.class, dataSource);
	}
}