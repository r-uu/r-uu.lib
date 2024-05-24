package de.ruu.lib.util.dto;

import static java.util.Objects.isNull;

/**
 * Interface for {@code D} types that provide {@link DTO}s via {@link #toDTO()}.
 *
 * @param <D> {@link DTO} type
 *
 * @author r-uu
 */
public interface DTOProvider<D extends DTO<?>>
{
	default D toDTO() { return toDTO(new MappedObjects()); };

	D toDTO(MappedObjects mappedObjects);

	/** @return {@code D} object that corresponds to this {@link DTO} */
	default D toDTO(MappedObjects mappedObjects, Class<D> dtoType)
	{
		if (isNull(mappedObjects)) throw new IllegalArgumentException("mappedObjects must not be null");
		return mappedObjects.getMappedInstance(this, dtoType);
	};
}