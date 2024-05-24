package de.ruu.lib.jpa.core;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;

import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PROTECTED;

/**
 * Mapped superclass for JPA entities that implements features of {@link Entity} interface. It is useful as base class
 * for {@link Entity} types that have a corresponding {@link AbstractDTO} implementation.
 *
 * @author r-uu
 */
@NoArgsConstructor(access = PROTECTED) // generate no args constructor for jpa, mapstruct, ...
@MappedSuperclass
@Getter
@Accessors(fluent = true) // generate fluent style getters but also implement java bean style getters
                          // to comply to java bean conventions
@EqualsAndHashCode
public abstract class AbstractEntity implements Entity<Long>
{
	@Serial private static final long serialVersionUID = 1L;

	/**
	 * may be <pre>null</pre> if instance was not (yet) persisted.
	 * <p>may not be modified from outside type hierarchy (from non-{@link AbstractEntity}-subclasses)
	 * <p>not {@code final} or {@code @NonNull} because otherwise there has to be a constructor with {@code id}-parameter
	 */
	@Setter(NONE) // redundant as long as there are no setters anyway, but just in case ...
	@Id @GeneratedValue protected Long id;

	/** may be <pre>null</pre> if {@link AbstractEntity} was not (yet) persisted. */
	@Version @Column(nullable = false)
	protected Short version;

	/**
	 * Picks the {@link AbstractDTO#getId()} and {@link AbstractDTO#getVersion()} values and assigns them to the
	 * respective fields in this class.
	 *
	 * @param dto
	 * @throws NullPointerException if {@code dto} is {@code null}
	 */
	protected AbstractEntity(@NonNull AbstractDTO dto)
	{
		this(); // just in case something important happens in the default constructor

		id      = dto.getId();;
		version = dto.getVersion();
	}

	/** mapstruct callback */
	public void beforeMapping(@NonNull AbstractDTO source)
	{
		// set fields that can not be modified from outside
		id      = source.getId();
		version = source.getVersion();
	}
}