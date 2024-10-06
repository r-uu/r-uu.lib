package de.ruu.lib.jpa.core;

import jakarta.persistence.PersistenceUnitTransactionType;
import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.sql.DataSource;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static jakarta.persistence.PersistenceUnitTransactionType.RESOURCE_LOCAL;
import static jakarta.persistence.SharedCacheMode.UNSPECIFIED;
import static jakarta.persistence.ValidationMode.AUTO;
import static java.lang.Thread.currentThread;

/** {@link #transactionType} is #RESOURCE_LOCAL by default */
@RequiredArgsConstructor
@Data
public abstract class AbstractPersistenceUnitInfo implements PersistenceUnitInfo
{
	public static final String JPA_VERSION = "3.0";

	public static final String PROPERTY_KEY_PERSISTENCE_UNIT_PERSISTENCE_UNIT_NAME =
			"persistence.unit.persistence.unit.name";
	public static final String PROPERTY_KEY_PERSISTENCE_UNIT_PERSISTENCE_PROVIDER_CLASS_NAME =
			"persistence.unit.persistence.provider.class.name";
	public static final String PROPERTY_KEY_PERSISTENCE_UNIT_TRANSACTION_TYPE =
			"persistence.unit.transaction.type";
	public static final String PROPERTY_KEY_PERSISTENCE_UNIT_JTA_DATA_SOURCE =
			"persistence.unit.jta.data.source";
	public static final String PROPERTY_KEY_PERSISTENCE_UNIT_NON_JTA_DATA_SOURCE =
			"persistence.unit.non.jta.data.source";
	public static final String PROPERTY_KEY_PERSISTENCE_UNIT_MAPPING_FILE_NAMES =
			"persistence.unit.mapping.file.names";
	public static final String PROPERTY_KEY_PERSISTENCE_UNIT_JAR_FILE_URLS =
			"persistence.unit.jar.file.urls";
	public static final String PROPERTY_KEY_PERSISTENCE_UNIT_PERSISTENCE_UNIT_ROOT_URL =
			"persistence.unit.persistence.unit.root.url";
	public static final String PROPERTY_KEY_PERSISTENCE_UNIT_MANAGED_CLASS_NAMES =
			"persistence.unit.managed.class.names";
	public static final String PROPERTY_KEY_PERSISTENCE_UNIT_EXCLUDE_UNLISTED_CLASSES =
			"persistence.unit.exclude.unlisted.classes";
	public static final String PROPERTY_KEY_PERSISTENCE_UNIT_SHARED_CACHE_MODE =
			"persistence.unit.shared.cache.mode";
	public static final String PROPERTY_KEY_PERSISTENCE_UNIT_VALIDATION_MODE =
			"persistence.unit.validation.mode";
	public static final String PROPERTY_KEY_PERSISTENCE_UNIT_PROPERTIES =
			"persistence.unit.properties";
	public static final String PROPERTY_KEY_PERSISTENCE_UNIT_PERSISTENCE_XML_SCHEMA_VERSION =
			"persistence.unit.persistence.xml.schema.version";
	public static final String PROPERTY_KEY_PERSISTENCE_UNIT_CLASS_LOADER =
			"persistence.unit.class.loader";
	public static final String PROPERTY_KEY_PERSISTENCE_UNIT_NEW_TEMP_CLASS_LOADER =
			"persistence.unit.new.temp.class.loader";

	private final String     persistenceUnitName;
	private final Class<?>   persistenceProvider;
	private final DataSource dataSource;

	@NonNull private List<String>                   mappingFileNames  = new ArrayList<>();
	@NonNull private List<URL>                      jarFileUrls       = new ArrayList<>();
	@NonNull private List<String>                   managedClassNames = new ArrayList<>();
	@NonNull private Properties                     properties        = new Properties();
	@NonNull private List<ClassTransformer>         classTransformers = new ArrayList<>();
	@NonNull private PersistenceUnitTransactionType transactionType   = RESOURCE_LOCAL;

	private URL             persistenceUnitRootUrl;
	@Accessors(fluent = true)
	private Boolean         excludeUnlistedClasses      = false;
	private SharedCacheMode sharedCacheMode             = UNSPECIFIED;
	private ValidationMode  validationMode              = AUTO;
	private String          persistenceXMLSchemaVersion = JPA_VERSION;
	private ClassLoader     newTempClassLoader;
	private ClassLoader     classLoader                 = currentThread().getContextClassLoader();

	@Override public String getPersistenceProviderClassName() { return persistenceProvider.getName(); }

	// TODO: find out if it makes sense to maintain only one datasource and return this for jta and
	//       resource local transaction mode
	@Override public DataSource getJtaDataSource()    { return dataSource; }
	@Override public DataSource getNonJtaDataSource() { return dataSource; }

	@Override public void addTransformer(ClassTransformer transformer)
	{
		classTransformers.add(transformer);
	}

	@Override public boolean excludeUnlistedClasses() { return excludeUnlistedClasses; }

	@Override public Properties getProperties()
	{
		Properties result = new Properties();

		result.put(PROPERTY_KEY_PERSISTENCE_UNIT_PERSISTENCE_UNIT_NAME,           persistenceUnitName);
		result.put(PROPERTY_KEY_PERSISTENCE_UNIT_TRANSACTION_TYPE,                transactionType);
		result.put(PROPERTY_KEY_PERSISTENCE_UNIT_MAPPING_FILE_NAMES,              mappingFileNames);
		result.put(PROPERTY_KEY_PERSISTENCE_UNIT_JAR_FILE_URLS,                   jarFileUrls);
		result.put(PROPERTY_KEY_PERSISTENCE_UNIT_MANAGED_CLASS_NAMES,             managedClassNames);
		result.put(PROPERTY_KEY_PERSISTENCE_UNIT_EXCLUDE_UNLISTED_CLASSES,        excludeUnlistedClasses);
		result.put(PROPERTY_KEY_PERSISTENCE_UNIT_SHARED_CACHE_MODE,               sharedCacheMode);
		result.put(PROPERTY_KEY_PERSISTENCE_UNIT_VALIDATION_MODE,                 validationMode);
		result.put(PROPERTY_KEY_PERSISTENCE_UNIT_PROPERTIES,                      properties);
		result.put(PROPERTY_KEY_PERSISTENCE_UNIT_PERSISTENCE_XML_SCHEMA_VERSION,  persistenceXMLSchemaVersion);
		result.put(PROPERTY_KEY_PERSISTENCE_UNIT_CLASS_LOADER,                    classLoader);

		if (persistenceProvider    != null)
				result.put(
						PROPERTY_KEY_PERSISTENCE_UNIT_PERSISTENCE_PROVIDER_CLASS_NAME,
						persistenceProvider.getName());
		if (dataSource             != null)
				result.put(PROPERTY_KEY_PERSISTENCE_UNIT_JTA_DATA_SOURCE,           dataSource);
		if (persistenceUnitRootUrl != null)
				result.put(PROPERTY_KEY_PERSISTENCE_UNIT_PERSISTENCE_UNIT_ROOT_URL, persistenceUnitRootUrl);
		if (newTempClassLoader     != null)
				result.put(PROPERTY_KEY_PERSISTENCE_UNIT_NEW_TEMP_CLASS_LOADER,     newTempClassLoader);

		return result;
	}

	/** remove this method after upgrade to new jpa version */
	@Override
	@Deprecated
	public jakarta.persistence.spi.PersistenceUnitTransactionType getTransactionType()
	{
		return jakarta.persistence.spi.PersistenceUnitTransactionType.RESOURCE_LOCAL;
	}

	public Properties properties() { return getProperties(); }

	public static List<String> toClassNameList(Set<Class<?>> managedClasses)
	{
		List<String> result = new ArrayList<>();
		managedClasses.forEach(c -> result.add(c.getName()));
		return result;
	}

	public void addManagedClass(Class<?> clazz)
	{
		managedClassNames.add(clazz.getName());
	}
}