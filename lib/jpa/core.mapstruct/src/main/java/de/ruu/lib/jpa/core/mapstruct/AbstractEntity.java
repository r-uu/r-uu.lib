package de.ruu.lib.jpa.core.mapstruct;

import de.ruu.lib.mapstruct.CyclicDependencyTracking;
import lombok.NonNull;

public abstract class AbstractEntity extends de.ruu.lib.jpa.core.AbstractEntity
{
	/**
	 * mapstruct callback, maps {@link AbstractDTO#getId()} and {@link
	 * AbstractDTO#getVersion()} values into {@link de.ruu.lib.jpa.core.AbstractEntity#id} and
	 * {@link de.ruu.lib.jpa.core.AbstractEntity#version} fields.
	 */
	protected void beforeMapping(
			@NonNull AbstractDTO source, @NonNull CyclicDependencyTracking context)
	{
		// set fields that can not be modified from outside
		if (source.optionalId     ().isPresent()) id      = source.optionalId     ().get();
		if (source.optionalVersion().isPresent()) version = source.optionalVersion().get();
	}

	/** mapstruct callback, no-op default implementation */
	protected void afterMapping(
			@NonNull AbstractDTO source, @NonNull CyclicDependencyTracking context) { }
}