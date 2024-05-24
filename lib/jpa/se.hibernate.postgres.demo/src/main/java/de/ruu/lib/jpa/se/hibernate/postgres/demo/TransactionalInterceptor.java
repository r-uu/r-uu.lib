package de.ruu.lib.jpa.se.hibernate.postgres.demo;

import de.ruu.lib.jpa.se.AbstractTransactionalInterceptor;
import de.ruu.lib.jpa.se.Transactional;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptor;
import jakarta.persistence.EntityManager;

/**
 * IMPORTANT
 * <br>
 * DO NOT FORGET TO MENTION THIS <code>@Interceptor</code> TYPE IN <code>beans.xml</code>!!!
 * <br>
 * OR TO INITIALISE SeContainer LIKE THIS:
 * <pre>
 * {@code
 * SeContainer container =
 *     SeContainerInitializer
 *         .newInstance()
 *         .enableInterceptors(TransactionalInterceptor.class)
 *         .selectAlternatives(MockGenerator.class)
 *         .initialize();
 * }
 * </pre>
 *
 * @author r-uu
 */
@Interceptor
@Priority(Interceptor.Priority.APPLICATION) // this makes the interceptor available in other modules / archives
@Transactional
public class TransactionalInterceptor extends AbstractTransactionalInterceptor
{
	@Inject private EntityManager entityManager;

	@Override protected EntityManager entityManager() { return entityManager; }
}