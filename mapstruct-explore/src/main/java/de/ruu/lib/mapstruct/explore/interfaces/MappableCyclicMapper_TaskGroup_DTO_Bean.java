package de.ruu.lib.mapstruct.explore.interfaces;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import de.ruu.lib.mapstruct.explore.common.TaskGroupBean;
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
@Mapper public interface MappableCyclicMapper_TaskGroup_DTO_Bean
{
	@NonNull TaskGroupDTO map(@NonNull TaskGroupDTO in, @NonNull @Context ReferenceCycleTracking context);

	/**
	 * annotating parameter {@code out} with {@link MappingTarget} is essential for this method being called as well as
	 * annotating parameter {@code context} with {@link Context}
	 */
	@BeforeMapping
	default void beforeMapping(
			@NonNull                TaskGroupDTO           in,
			@NonNull @MappingTarget TaskGroupBean          out,
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
			@NonNull                TaskGroupDTO           in,
			@NonNull @MappingTarget TaskGroupBean          out,
			@NonNull @Context       ReferenceCycleTracking context)
	{
		out.afterMapping(in, context);
	}

	default @NonNull Class<TaskGroupBean> outType() { return TaskGroupBean.class; };
	@NonNull TaskGroupBean        create(@NonNull TaskGroupEntityDTO in, @NonNull ReferenceCycleTracking context);

	/**
	 * object factory will be called by mapstruct during generated {@link #map(TaskGroupDTO, ReferenceCycleTracking)}
	 * implementation
	 */
	@ObjectFactory
	default @NonNull TaskGroupBean lookupOrCreate(@NonNull TaskGroupEntityDTO in, @NonNull @Context ReferenceCycleTracking context)
	{
		TaskGroupBean out = context.get(in, outType());
		if (out == null)
		{
			out = create(in, context);
//			context.put(in, out); // mapstruct will put in and out into context directly after this method returns
		}
		return out;
	}
}