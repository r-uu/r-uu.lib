package de.ruu.lib.util.dto;

import java.io.Serializable;

import static java.util.Objects.isNull;

/**
 * Interface for <b>D</b>ata <b>T</b>ransfer <b>O</b>bjects ({@link DTO})s. A {@link DTO} is typically used for data
 * transfer between distributed systems running in separate processes. A {@link DTO} corresponds to a {@link
 * DTOProvider} object of type {@code S}. {@link DTO} instances provide a {@link DTOProvider} instance of type {@code S}
 * via {@link #toSource()}.
 * <p>
 * Data structures often have circular dependencies. These have to be handled properly during serialisation /
 * deserialisation for data transfer. To avoid infinite recursion and {@link StackOverflowError}s during processing of
 * data with circular dependencies {@link DTO} implementations have to make decisions about where and how to break up
 * such cycles.
 * <p>
 * {@link MappedObjects} in {@link #toSource(MappedObjects)} and {@link #toSource(MappedObjects, Class)} helps to make
 * this decision: implementations of {@link #toSource(MappedObjects)} typically follow the following pattern:
 * <ul>
 *   <li>Check if an already mapped instance of the {@link DTOProvider} can be found for the {@link DTO} (i. e. search
 *       for {@code this} in {@link MappedObjects}). If a {@link DTOProvider} of type {@code S} return it immediately.
 *   </li>
 *   <li>Otherwise create a new {@link DTOProvider} and hand over {@link MappedObjects} together with {@code this} to
 *       the constructor. It is the constructor's responsibility to traverse related objects of {@code this} and to
 *       create {@link DTOProvider} instances for them recursively. Again {@link MappedObjects} help to avoid infinite
 *       recursion.
 *   </li>
 *   <li>Make sure that the {@link DTOProvider} constructor stores the new instance ({@code this}) in {@link
 *       MappedObjects} via {@link MappedObjects#storeMappedInstance(Object, Object)}.</li>
 * </ul>
 *
 * @param <S> source object type
 *
 * @author r-uu
 */
public interface DTO<S extends DTOProvider<?>> extends Serializable
{
	/**
	 * Starts mapping of {@link DTO} to {@link DTOProvider}.
	 * @return mapped dto source
	 */
	default S toSource() { return toSource(new MappedObjects()); };

	/**
	 * Starts mapping of {@link DTO} to {@link DTOProvider}.
	 * @return mapped dto source
	 */
	S toSource(MappedObjects mappedObjects);

	/**
	 * Implement this method so that it does not run into infinite recursion (see {@link DTO}).
	 * @param mappedObjects {@link DTO}s that had already been mapped
	 * @param sourceType type of source object that corresponds to this {@link DTO}
	 * @return source object of type {@code T} that corresponds to this {@link DTO}. This default implementation
	 *         returns {@code null} if this DTO had not been mapped yet.
	 * @throws IllegalArgumentException if {@code mappedObjects} or {@code targetType} is {@code null}
	 */
	default S toSource(MappedObjects mappedObjects, Class<S> sourceType)
	{
		if (isNull(mappedObjects)) throw new IllegalArgumentException("mappedObjects must not be null");
		return mappedObjects.getMappedInstance(this, sourceType);
	}
}