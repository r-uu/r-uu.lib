package de.ruu.lib.jpa.core;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;

import static lombok.AccessLevel.PROTECTED;

/**
 * Base class of <b>D</b>ata <b>T</b>ransfer <b>O</b>bjects (DTOs) for corresponding {@link AbstractEntity} classes.
 * DTOs provide the following features for abstract entities:
 * <ul>
 *   <li>State transfer for entities to remote systems. Transfer is typically handled by http using rest with json but
 *       is generally technology independent.</li>
 *   <li>Preservation of encapsulation: {@link #id} and {@link #version} are injected into constructor and can not be
 *       modified afterwards.</li>
 * </ul>
 *
 * @author r-uu
 */
@NoArgsConstructor(access = PROTECTED) // generate no args constructor for jsonb, jaxb, mapstruct, ...
@Getter
@Accessors(fluent = true) // generate fluent style getters but also implement java bean style getters
                          // to comply to java bean conventions
@ToString
@EqualsAndHashCode
public abstract class AbstractDTO implements Entity<Long>
{
	@Serial private static final long serialVersionUID = 1L;

	/**
	 * May be <pre>null</pre> if corresponding {@link AbstractEntity} was not (yet) persisted.<p>
	 * May not be modified from outside type hierarchy (from non-{@link AbstractDTO}-subclasses).<p>
	 * Not {@code final} or {@code @NonNull} because otherwise there would have to be a constructor
	 * with {@code id}-parameter
	 */
	protected Long id;

	/**
	 * may be <pre>null</pre> if corresponding {@link AbstractEntity} was not (yet) persisted.
	 * <p>may not be modified from outside type hierarchy (from non-{@link AbstractEntity}-subclasses)
	 * <p>not {@code final} or {@code @NonNull} because otherwise there has to be a constructor with {@code id}-parameter
	 */
	protected Short version;

	/**
	 * Use this constructor on the server side where an {@link AbstractEntity} is available.
	 *
	 * @param entity that corresponds to this {@link AbstractDTO} instance
	 * @throws NullPointerException if {@code entity} is {@code null}
	 */
	protected AbstractDTO(@NonNull AbstractEntity entity)
	{
		this(); // just in case something important happens in the default constructor

		id      = entity.getId();
		version = entity.getVersion();
	}

	/** mapstruct callback */
	public void beforeMapping(@NonNull AbstractEntity source)
	{
		// set fields that can not be modified from outside
		id      = source.getId();
		version = source.getVersion();
	}
}