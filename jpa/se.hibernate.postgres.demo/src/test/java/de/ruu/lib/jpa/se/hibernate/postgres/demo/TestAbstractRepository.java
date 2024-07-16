package de.ruu.lib.jpa.se.hibernate.postgres.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;

import de.ruu.lib.jpa.se.TransactionalInterceptorCDI;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TransactionRequiredException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.ruu.lib.cdi.common.CDIExtension;
import de.ruu.lib.cdi.se.CDIContainer;
import de.ruu.lib.junit.DisabledOnServerNotListening;
import jakarta.enterprise.inject.spi.CDI;
import lombok.extern.slf4j.Slf4j;

@DisabledOnServerNotListening
(
		propertyNameHost = "de.ruu.lib.jpa.se.hibernate.postgres.AbstractEntityManagerProducer.dbhost",
		propertyNamePort = "de.ruu.lib.jpa.se.hibernate.postgres.AbstractEntityManagerProducer.dbport"
)
@Slf4j class TestAbstractRepository
{
	/** bootstrap CDI in Java SE with {@link TransactionalInterceptorCDI} */
	@BeforeAll static void beforeAll()
	{
		CDIContainer.bootstrap(
				TestAbstractRepository.class.getClassLoader(),
//				List.of(TransactionalInterceptorCDI.class),
				List.of(CDIExtension.class));
		log.debug("cdi bootstrapped successfully");
	}

	private SimpleTypeRepository repository;

	@BeforeEach void beforeEach()
	{
		repository = CDI.current().select(SimpleTypeRepository.class).get();
		assertThat(repository, is(not(nullValue())));
	}

	@Test void testAbstractRepositoryWithoutTransaction()
	{
		Exception exception =
				assertThrows(TransactionRequiredException.class, () ->
				{
						String name = "schmottekk";
						SimpleTypeEntity entity = new SimpleTypeEntity(name);
						entity = repository.save(entity);
				});
	}

	@Test void testAbstractRepositoryWithTransaction()
	{
		EntityTransaction transaction = repository.entityManager().getTransaction();

		transaction.begin();

		String name = "schmottekk";
		SimpleTypeEntity entity = new SimpleTypeEntity(name);
		entity = repository.save(entity);
		assertThat(entity       , is(not(nullValue())));
		assertThat(entity.id()  , is(not(nullValue())));
		assertThat(entity.name(), is(name));

		name = "äffchen";
		entity.name(name);
		entity = repository.save(entity);
		assertThat(entity       , is(not(nullValue())));
		assertThat(entity.id()  , is(not(nullValue())));
		assertThat(entity.name(), is(name));

		repository.delete(entity.getId());
		Optional<SimpleTypeEntity> optional = repository.find(entity.getId());
		assertThat(optional            , is(not(nullValue())));
		assertThat(optional.isPresent(), is(false));

		transaction.commit();
	}
}