package de.ruu.lib.mapstruct;

import java.util.IdentityHashMap;
import java.util.Map;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

/** used as {@link org.mapstruct.Context} parameter in mapstruct mappings */
public class CyclicDependencyTracking
{
	private final Map<Object, Object> knownInstances = new IdentityHashMap<>();

	@BeforeMapping
	public <T> T getMappedInstance(Object source, @TargetType Class<T> targetType)
	{
		return targetType.cast(knownInstances.get(source));
	}

	@BeforeMapping // TODO find out why @AfterMapping is not used here
	public void storeMappedInstance(Object source, @MappingTarget Object target)
	{
		knownInstances.put(source, target);
	}
}