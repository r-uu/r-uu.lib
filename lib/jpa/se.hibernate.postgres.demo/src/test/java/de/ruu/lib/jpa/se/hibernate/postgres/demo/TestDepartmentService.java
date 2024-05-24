package de.ruu.lib.jpa.se.hibernate.postgres.demo;

import de.ruu.lib.cdi.common.CDIExtension;
import de.ruu.lib.junit.DisabledOnServerNotListening;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@DisabledOnServerNotListening(propertyNameHost = "database.host", propertyNamePort = "database.port")
@Slf4j class TestDepartmentService
{
	private static SeContainer seContainer; // initialisation and closure handled in before/after all methods

	@SuppressWarnings("unchecked")
	@BeforeAll static void beforeAll()
	{
		log.debug("cdi container initialisation");
		try
		{
			seContainer =
					SeContainerInitializer
							.newInstance()
							.addExtensions(CDIExtension.class)
							.enableInterceptors(TransactionalInterceptor.class)
							.initialize();
		}
		catch (Exception e)
		{
			log.error("failure initialising seContainer", e);
		}
		log.debug("cdi container initialisation {}", seContainer == null ? "unsuccessful" : "successful");
	}

	@AfterAll static void afterAll()
	{
		log.debug("cdi container shut down");
		seContainer.close();
		log.debug("cdi container shut down {}", seContainer.isRunning() ? "unsuccessful" : "successful");
	}

	@Test void testDepartmentService()
	{
		DepartmentService service = seContainer.select(DepartmentService.class).get();
		assertThat(service, is(not(nullValue())));

		String name = "schmottekk";
		DepartmentEntity entity = new DepartmentEntity(name);
		entity = service.save(entity);
		assertThat(entity, is(not(nullValue())));
		assertThat(entity.id(), is(not(nullValue())));
		assertThat(entity.name(), is(name));

		name = "äffchen";
		entity.name(name);
		entity = service.save(entity);
		assertThat(entity, is(not(nullValue())));
		assertThat(entity.id(), is(not(nullValue())));
		assertThat(entity.name(), is(name));

		service.delete(entity.getId());
		Optional<DepartmentEntity> optional = service.find(entity.getId());
		assertThat(optional, is(not(nullValue())));
		assertThat(optional.isPresent(), is(false));
	}
}