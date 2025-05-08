package de.ruu.lib.jpa.se;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;

/**
 * Abstract super class for transactional interceptors. Extend this class to provide a suitable {@link #entityManager()}
 * implementation.
 *
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
 *         .addBeanClasses    (<ExtensionOf>TransactionalInterceptorCDI.class)
 *         .enableInterceptors(<ExtensionOf>TransactionalInterceptorCDI.class)
 *         .selectAlternatives(MockGenerator.class)
 *         .initialize();
 * }
 * </pre>
 *
 * @author r-uu
 */
@Slf4j
public abstract class AbstractTransactionalInterceptor
{
	protected abstract EntityManager entityManager();

	@AroundInvoke
	public Object transaction(InvocationContext context) throws Exception
	{
		Object            result;
		boolean           startedTransaction = false;
		EntityTransaction transaction        = entityManager().getTransaction();
		String            methodName         =
				context.getMethod().getDeclaringClass().getName() + "." + context.getMethod().getName();

		log.trace("\nentity manager {}, transaction {}", entityManager().hashCode(), transaction.hashCode());

		try
		{
			if (transaction.isActive())
			{
				log.debug("resuming active transaction {}", transaction);
			}
			else
			{
				log.debug("starting transaction {}", transaction);
				transaction.begin();
				startedTransaction = true;
			}

			log.trace("calling           {} in transaction {}", methodName, transaction);
			result = context.proceed();
			log.trace("returned from     {} in transaction {}", methodName, transaction);
		}
		catch (Throwable t)
		{
			log.error("failure executing {} in transaction {}", methodName, transaction, t);
			throw t;
		}
		finally
		{
			if (startedTransaction)
			{
				if (transaction.isActive())
				{
					try
					{
						log.debug("committing transaction {}", transaction);
						transaction.commit();
						log.debug("commit succeeded, transaction {}", transaction);
					}
					catch (RuntimeException e)
					{
						log.debug("rolling back transaction {}", transaction);
						transaction.rollback();
						log.debug("rollback succeeded, transaction {}", transaction);
					}
				}
				else
				{
					log.warn("can't commit inactive transaction {}", transaction);
				}
			}
			else
			{
				log.trace("resuming transaction {}", transaction);
			}
		}

		return result;
	}
}