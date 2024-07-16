package de.ruu.lib.jpa.core;

import lombok.NonNull;

import java.io.Serializable;
import java.util.Optional;

/**
 * Defines common features primary key ({@link #getId()}) and version {@link #getVersion()} for JPA entities and their
 * corresponding DTOs.
 *
 * @param <I> type of primary key, it has to be serializable
 * @param <D> type of corresponding {@link DTO}
 *
 * @author r-uu
 */
public interface Entity<I extends Serializable, D extends DTO<I, ?>> extends Serializable
{
	/** name of the field (property) that represents primary key. */
	String P_ID = "id";

	// fluent style accessors
	/** @return primary key, may be {@code null}, {@code null} indicates that entity was not (yet) persisted. */
	I     id();
	/** @return version, may be {@code null}, {@code null} indicates that entity was not (yet) persisted. */
	Short version();

	// java bean style accessors for those who do not work with fluent style accessors (mapstruct)
	/** @return primary key, may be {@code null}, {@code null} indicates that entity was not (yet) persisted. */
	default I     getId()      { return id();      };
	/** @return version, may be {@code null}, {@code null} indicates that entity was not (yet) persisted. */
	default Short getVersion() { return version(); };

	/** @return optional primary key, {@link Optional#empty()} indicates that entity was not (yet) persisted. */
	default public @NonNull Optional<I>     optionalId()      { return Optional.ofNullable(id());      }
	/** @return optional version, {@link Optional#empty()} indicates that entity was not (yet) persisted. */
	default public @NonNull Optional<Short> optionalVersion() { return Optional.ofNullable(version()); }
}