package de.ruu.lib.util.classpath;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static de.ruu.lib.util.StringBuilders.sb;
import static de.ruu.lib.util.classpath.Classpath.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@Disabled // log level for this class is set to info in log4j2.xml
@Slf4j
class ClasspathTest
{
	@Test void testGetClasspathResourcesByName() throws IOException
	{
		final Map<String, List<ClasspathResource>> classpathResourceLists = getClasspathResources();

		assertTrue(classpathResourceLists.size() > 0);

		final Set<String> resourceNamesSorted = new TreeSet<>(classpathResourceLists.keySet());

		final StringBuilder stringBuilder = sb();

		for (final String resourceName : resourceNamesSorted)
		{
			stringBuilder.append("\nresource name " + resourceName);

			for (final ClasspathResource classpathResource : classpathResourceLists.get(resourceName))
			{
				stringBuilder.append
				(
						"\n\tresource container " +
						classpathResource.getResourceContainer().getResourceContainerFile().getAbsolutePath()
				);
			}
		}

		stringBuilder.append("\nnumber of resources: " + classpathResourceLists.size());

		log.debug(stringBuilder.toString());
	}

	private final static String FILTERED_RESOURCE_CONTAINER_NAME =
			System.getProperty("java.class.path").split(System.getProperty("path.separator"))[0];

	@Test void testGetClasspathResourcesWithResourceContainerFilter() throws IOException
	{
		final FileFilter resourceContainerFilter =
				new FileFilter()
				{
					@Override
					public boolean accept(final File file)
					{
						if (file.getPath().equals(FILTERED_RESOURCE_CONTAINER_NAME))
						{
							log.debug("filtering resource container: " + FILTERED_RESOURCE_CONTAINER_NAME);
							return false;
						}
						return true;
					}
				};

		final Map<String, List<ClasspathResource>> classpathResourcesWithoutFilter = getClasspathResources();
		final Map<String, List<ClasspathResource>> classpathResourcesWithFilter    =
				getClasspathResources(resourceContainerFilter, FILTER_ACCEPT_FILES_ALL, FILTER_ACCEPT_ZIP_ENTRIES_ALL);

		assertTrue(classpathResourcesWithoutFilter.size() > classpathResourcesWithFilter.size());

		log.debug(
				"\nnumber of resources with filter: " + classpathResourcesWithFilter.size() +
				"\nnumber of resources without filter: " + classpathResourcesWithoutFilter.size() +
				"\nclasspath\n" + System.getProperty("java.class.path"));
	}

	@Test void testGetClasspathResourcesWithDirectoryResourceFilter() throws IOException
	{
		final FileFilter resourceContainerFilter = FILTER_ACCEPT_FILES_ALL;
		final FileFilter directoryResourceFilter = FILTER_ACCEPT_FILES_DIRECTORIES_ONLY;

		final Map<String, List<ClasspathResource>> classpathResourceListsWithoutFilter = getClasspathResources();
		final Map<String, List<ClasspathResource>> classpathResourceListsWithFilter    =
				getClasspathResources(resourceContainerFilter, directoryResourceFilter, FILTER_ACCEPT_ZIP_ENTRIES_ALL);

		assertTrue(classpathResourceListsWithoutFilter.size() > classpathResourceListsWithFilter.size());

		log.debug(
				"\nnumber of resources with filter: " + classpathResourceListsWithFilter.size() +
				"\nnumber of resources without filter: " + classpathResourceListsWithoutFilter.size());
	}

	@Test void testGetClasspathPackages() throws IOException
	{
		final Map<String, List<ClasspathResource>> classpathResourceListsWithoutFilter = getClasspathResources();
		final Map<String, List<ClasspathResource>> classpathResourceListsWithFilter    = getPackagesAsClasspathResources();

		assertTrue(classpathResourceListsWithoutFilter.size() > classpathResourceListsWithFilter.size());

		log.debug(
				"\nnumber of resources with filter: " + classpathResourceListsWithFilter.size() +
				"\nnumber of resources without filter: " + classpathResourceListsWithoutFilter.size());
	}

	@Test void testReportClasspathResourcesByResourceContainerName() throws IOException
	{
		log.debug(
				"class path resources by resource container name\n{}",
				Classpath.reportClasspathResourcesByName(getClasspathResources()));
	}
}