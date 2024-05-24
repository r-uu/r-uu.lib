package de.ruu.lib.jpa.core;

import java.io.Serializable;
import java.util.Optional;

/**
 * Defines common features primary key ({@link #getId()}) and version {@link #getVersion()} for JPA entities and their
 * corresponding DTOs.
 *
 * @param <I> type of primary key, it has to be serializable
 *
 * @author r-uu
 */
public interface Entity<I extends Serializable> extends Serializable
{
	/** name of the field (property) that represents primary key. */
	String P_ID = "id";

	// fluent style accessors
	I     id();
	Short version();

	// java bean style accessors
	default I     getId()      { return id();      };
	default Short getVersion() { return version(); };

	default public Optional<I>     optionalId()      { return Optional.ofNullable(id());      }
	default public Optional<Short> optionalVersion() { return Optional.ofNullable(version()); }
}