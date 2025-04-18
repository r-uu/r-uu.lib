package de.ruu.lib.mapstruct.explore.interfaces;

import de.ruu.lib.mapstruct.MappableCyclic;
import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import de.ruu.lib.mapstruct.explore.common.TaskGroupDTO;
import de.ruu.lib.mapstruct.explore.common.TaskGroupEntityDTO;
import lombok.NonNull;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ObjectFactory;

/**
 * this class should be identical to {@link de.ruu.lib.mapstruct.MappableCyclicMapper} and exists to test without
 * dependency to code in production.
 */
@Mapper public interface Map_TaskGroup_EntityDTO_DTO extends MappableCyclicMapper<TaskGroupEntityDTO, TaskGroupDTO>
{
	@NonNull TaskGroupDTO map(@NonNull TaskGroupEntityDTO in, @NonNull @Context ReferenceCycleTracking context);

	/**
	 * annotating parameter {@code out} with {@link MappingTarget} is essential for this method being called as well as
	 * annotating parameter {@code context} with {@link Context}
	 */
	@BeforeMapping
	default void beforeMapping(
			@NonNull                TaskGroupEntityDTO              in,
			@NonNull @MappingTarget TaskGroupDTO                    out,
			@NonNull @Context       ReferenceCycleTracking context)
	{
		out.beforeMapping(in, context);
	}

	/**
	 * annotating parameter {@code out} with {@link MappingTarget} is essential for this method being called as well as
	 * annotating parameter {@code context} with {@link Context}
	 */
	@AfterMapping
	default void afterMapping(
			@NonNull                TaskGroupEntityDTO                     in,
			@NonNull @MappingTarget TaskGroupDTO                    out,
			@NonNull @Context       ReferenceCycleTracking context)
	{
		out.afterMapping(in, context);
	}

	@NonNull Class<OUT> outType();
	@NonNull OUT        create(@NonNull TaskGroupEntityDTO in, @NonNull ReferenceCycleTracking context);

	/**
	 * object factory will be called by mapstruct during generated {@link #map(MappableCyclic, ReferenceCycleTracking)}
	 * implementation
	 */
	@ObjectFactory
	default @NonNull OUT lookupOrCreate(@NonNull TaskGroupEntityDTO in, @NonNull @Context ReferenceCycleTracking context)
	{
		OUT out = context.get(in, outType());
		if (out == null)
		{
			out = create(in, context);
//			context.put(in, out); // mapstruct will put in and out into context directly after this method returns
		}
		return out;
	}
}