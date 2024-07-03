package de.ruu.lib.util.bimapped;

import lombok.NonNull;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Optional;

public class BiMap
{
	private final Map<Object, Object> map = new IdentityHashMap<>();

	/**
	 * {@link Optional} with non {@code null} value if a value with key {@code source} is stored in {@link
	 * #map}. Otherwise, returned {@link Optional#isEmpty()} is {@code true}.
	 *
	 * @param source     see above
	 * @param targetType see above
	 * @return           see above
	 * @param <T> type of non {@code null} return value
	 * @throws ClassCastException if non {@code null} value for {@code source} can not be cast to {@code
	 *                            targetType}
	 */
	public <T> Optional<T> lookup(@NonNull Object source, @NonNull Class<T> targetType)
	{
		return Optional.ofNullable(get(source, targetType));
	}

	/**
	 * Non {@code null} T value if a value with key {@code source} is stored in {@link #map}, otherwise {@code null}.
	 *
	 * @param source     see above
	 * @param targetType see above
	 * @return           see above
	 * @param <T> type of non {@code null} return value
	 * @throws ClassCastException if non {@code null} value for {@code source} can not be cast to {@code
	 *                            targetType}
	 */
	public <T> T get(@NonNull Object source, @NonNull Class<T> targetType)
	{
		return targetType.cast(map.get(source));
	}

	/**
	 * Inserts {@code source} and {@code target} into {@link #map}.
	 *
	 * @param source
	 * @param target
	 * @throws NullPointerException if {@code source} or {@code target} are {@code null}
	 * @throws IllegalArgumentException if {@code source} and {@code target} do not have the same type or
	 *                                  one of them is already contained as key in {@link #map}.
	 */
	public void put(@NonNull Object source, @NonNull Object target)
	{
		if (source.getClass().equals(target.getClass()))
				throw new IllegalArgumentException(
						"source and target objects must not have the same type " + source.getClass().getName());

		Object object = map.get(source);

		if (object != null)
		{
			if (object != target) throw new IllegalArgumentException("reassigning new target value not supported");
		}

		object = map.get(target);

		if (object != null)
		{
			if (object != source) throw new IllegalArgumentException("reassigning new source key not supported");
		}

		map.put(source, target);
		map.put(target, source);
	}
}