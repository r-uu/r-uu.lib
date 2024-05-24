package de.ruu.lib.jpa.core;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import de.ruu.lib.jpa.core.criteria.Criteria;
import de.ruu.lib.jpa.core.criteria.restriction.Restrictions;
import de.ruu.lib.util.Reflection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Query;

/**
 * Abstract implementation of generic DAO.
 *
 * @param <T> entity type, it implements at least {@code Entity<I>}
 * @param <I> entity's primary key, it has to be {@link Serializable}
 * @see Entity
 *
 * @author r-uu
 */
public abstract class AbstractRepository<T extends Entity<I>, I extends Serializable>
		implements Repository<T, I>, AutoCloseable
{
	private final Class<Entity<I>> clazz;

	/**
	 * Determines value for {@link #clazz} from type arguments of implementing class by reflection. Also works for proxied
	 * instances in e.g. CDI environments.
	 */
	@SuppressWarnings(value = "unchecked")
	protected AbstractRepository()
	{
		Optional<ParameterizedType> optional = Reflection.getFirstParameterizedTypeInParents(getClass());

		if (optional.isPresent())
		{
			ParameterizedType parameterizedTypeForAbstractDAO = optional.get();

			Type type = parameterizedTypeForAbstractDAO.getActualTypeArguments()[0];

			try
			{
				clazz = (Class<Entity<I>>) Class.forName(type.getTypeName());
			}
			catch (ClassNotFoundException e)
			{
				throw new IllegalStateException("could not find class " + type.getTypeName(), e);
			}
		}
		else
		{
			throw new IllegalStateException("could not get parameterized type in parents");
		}
	}

	protected abstract EntityManager getEntityManager();

	@Override public Class<Entity<I>> getEntityClass() { return clazz; }

	@SuppressWarnings(value = "unchecked")
	@Override
	public Optional<T> find(I id) { return Optional.ofNullable((T) getEntityManager().find(clazz, id)); }

	@SuppressWarnings(value = "unchecked")
	@Override
	public List<T> find(I... ids)
	{
		return findByCriteria(Criteria.forClass(clazz).add(Restrictions.in(Entity.P_ID, ids)));
	}

	@SuppressWarnings(value = "unchecked")
	@Override
	public List<T> findAll() { return findByCriteria(Criteria.forClass(clazz)); }

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedQuery(final String name, Object... params) {

		Query query = getEntityManager().createNamedQuery(name);

		for (int i = 0; i < params.length; i++)
		{
			query.setParameter(i + 1, params[i]);
		}

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override public List<T> findByNamedQueryAndNamedParams(final String name, final Map<String, ?> params)
	{
		Query query = getEntityManager().createNamedQuery(name);

		params.forEach((key, value) -> query.setParameter(key, value));

		return query.getResultList();
	}

	@Override public Long countAll()
	{
		return
				getEntityManager()
						.createQuery("select count(*) from " + clazz.getSimpleName(), Long.class)
						.getSingleResult();
	}

	@Override public boolean isDetached(final T object)
	{
		if (object.optionalId().isPresent())                // must not be transient
		{
			if (getEntityManager().contains(object) == false) // must not be managed now
			{
				if (find(object.getId()) != null)               // must not be removed
				{
					return true;
				}
			}
		}
		return false;
	}

	@Override public T save(final T object)
	{
		T result;
		EntityManager entityManager = getEntityManager();

		if (object.getId() != null)
		{
			result = entityManager.merge(object);
		}
		else
		{
			entityManager.persist(object);
			result = object;
		}

		entityManager.flush(); // execute flush to update version to the correct value

		return result;
	}

	//	@SafeVarargs
	@Override public void save(final T... entities)
	{
		for (T entity : entities)
		{
			save(entity);
		}
	}

	@Override public void delete(final I id)
	{
		// unchecked access to optional is acceptable here because null handling (throw IllegalArgumentException) will be
		// done in called delete(T) method
		delete(find(id).get());
	}

	@Override public void delete(T entity)
	{
		getEntityManager().remove(entity);
	}

	@Override public void refresh(final T entity)
	{
		getEntityManager().refresh(entity);
	}

	@Override public void flushAndClear()
	{
		getEntityManager().flush();
		getEntityManager().clear();
	}

	/**
	 * Retrieve objects using criteria. It is equivalent to <code>criteria.list(entityManager)</code>.
	 *
	 * @param criteria criteria which will be executed
	 * @return list of founded objects
	 * @see Query#getResultList()
	 */
	protected List findByCriteria(Criteria criteria)
	{
		return criteria.list(getEntityManager());
	}

	/**
	 * Retrieve an unique object using criteria. It is equivalent to
	 * <code>criteria.uniqueResult(entityManager)</code>.
	 *
	 * @param criteria criteria which will be executed
	 * @return retrieved object
	 * @throws NoResultException - if there is no result
	 * @throws NonUniqueResultException - if more than one result
	 * @see Query#getSingleResult()
	 */
	protected Object findUniqueByCriteria(Criteria criteria) throws NonUniqueResultException, NoResultException
	{
		return criteria.uniqueResult(getEntityManager());
	}

	@Override public void close() throws Exception
	{
		EntityManager entityManager = getEntityManager();

		if (entityManager.isOpen())
		{
			entityManager.getTransaction().begin();
			entityManager.flush();
			entityManager.getTransaction().commit();
			entityManager.close();
		}
	}
}