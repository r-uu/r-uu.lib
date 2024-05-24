package de.ruu.lib.util.dto;

import java.util.IdentityHashMap;
import java.util.Map;

public class MappedObjects
{
	private final Map<Object, Object> mappedObjects = new IdentityHashMap<>();

	public <T> T getMappedInstance(Object source, Class<T> targetType)
	{
		return targetType.cast(mappedObjects.get(source));
	}

	public void storeMappedInstance(Object source, Object target)
	{
		mappedObjects.put(source, target);
	}
}
