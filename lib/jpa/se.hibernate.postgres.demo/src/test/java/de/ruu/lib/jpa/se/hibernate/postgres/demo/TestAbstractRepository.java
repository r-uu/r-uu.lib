package de.ruu.lib.jpa.se.hibernate.postgres.demo;

import de.ruu.lib.cdi.common.CDIExtension;
import de.ruu.lib.cdi.se.CDIContainer;
import de.ruu.lib.junit.DisabledOnServerNotListening;
import jakarta.enterprise.inject.spi.CDI;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@DisabledOnServerNotListening(propertyNameHost = "database.host", propertyNamePort = "database.port")
@Slf4j class TestAbstractRepository
{
	/** bootstrap CDI in Java SE with {@link TransactionalInterceptor} */
	@BeforeAll static void beforeAll()
	{
		CDIContainer.bootstrap(
				TestAbstractRepository.class.getClassLoader(),
				List.of(TransactionalInterceptor.class),
				List.of(CDIExtension.class));
		log.debug("cdi bootstrapped successfully");
	}

	private DepartmentRepository repository;

	@BeforeEach void beforeEach()
	{
		repository = CDI.current().select(DepartmentRepository.class).get();
		assertThat(repository, is(not(nullValue())));
	}

	@Test void testAbstractRepository()
	{
		String name = "schmottekk";
		DepartmentEntity entity = new DepartmentEntity(name);
		entity = repository.save(entity);
		assertThat(entity, is(not(nullValue())));
		assertThat(entity.id(), is(not(nullValue())));
		assertThat(entity.name(), is(name));

		name = "äffchen";
		entity.name(name);
		entity = repository.save(entity);
		assertThat(entity, is(not(nullValue())));
		assertThat(entity.id(), is(not(nullValue())));
		assertThat(entity.name(), is(name));

		repository.delete(entity.getId());
		Optional<DepartmentEntity> optional = repository.find(entity.getId());
		assertThat(optional, is(not(nullValue())));
		assertThat(optional.isPresent(), is(false));
	}
}