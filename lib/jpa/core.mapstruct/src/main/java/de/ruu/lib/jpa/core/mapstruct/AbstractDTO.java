package de.ruu.lib.jpa.core.mapstruct;

import de.ruu.lib.mapstruct.CyclicDependencyTracking;
import lombok.NonNull;

public class AbstractDTO extends de.ruu.lib.jpa.core.AbstractDTO
{
	/**
	 * mapstruct callback, maps {@link AbstractEntity#getId()} and {@link
	 * AbstractEntity#getVersion()} values into {@link de.ruu.lib.jpa.core.AbstractDTO#id} and
	 * {@link de.ruu.lib.jpa.core.AbstractDTO#version} fields.
	 */
	protected void beforeMapping(
			@NonNull AbstractEntity source, @NonNull CyclicDependencyTracking context)
	{
		// set fields that can not be modified from outside
		if (source.optionalId     ().isPresent()) id      = source.optionalId     ().get();
		if (source.optionalVersion().isPresent()) version = source.optionalVersion().get();
	}

	/** mapstruct callback, no-op default implementation */
	protected void afterMapping(
			@NonNull AbstractEntity source, @NonNull CyclicDependencyTracking context) { }
}