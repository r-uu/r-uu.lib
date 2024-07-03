package de.ruu.lib.mapstruct;

import lombok.NonNull;

/**
 * Interface for types that can be mapped to {@code T}.
 *
 * @param <T> {@link BiMappedTarget} type
 *
 * @author r-uu
 */
public interface BiMappedSource<T extends BiMappedTarget<?>>
{
	/**
	 * map values from {@code input} into private / protected fields of {@code this} {@link BiMappedSource}
	 * @param input
	 */
	void beforeMapping(@NonNull T input);
	void afterMapping (@NonNull T input);

	@NonNull T toTarget();
}