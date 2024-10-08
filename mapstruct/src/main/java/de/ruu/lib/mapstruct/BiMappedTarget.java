package de.ruu.lib.mapstruct;

import lombok.NonNull;

/**
 * Interface for types that can be mapped to {@code S}.
 *
 * @param <S> {@link BiMappedSource} type
 *
 * @author r-uu
 */
public interface BiMappedTarget<S extends BiMappedSource<?>>
{
	/**
	 * map values from {@code input} into private / protected fields of {@code this} {@link BiMappedTarget}
	 * @param input
	 */
	void beforeMapping(@NonNull S input);
	void afterMapping (@NonNull S input);

	@NonNull S toSource();
}