package de.ruu.lib.mapstruct.explore.jeeeraaah.common.jpadto;

import de.ruu.lib.mapstruct.MappableCyclicMapper;
import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import lombok.NonNull;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;
import org.mapstruct.factory.Mappers;

/** {@link TaskGroupEntityDTO} -> {@link TaskGroupEntityJPA} */
@Mapper interface Map_TaskGroup_EntityDTO_EntityJPA extends MappableCyclicMapper<TaskGroupEntityDTO, TaskGroupEntityJPA>
{
	Map_TaskGroup_EntityDTO_EntityJPA INSTANCE = Mappers.getMapper(Map_TaskGroup_EntityDTO_EntityJPA.class);

	@NonNull TaskGroupEntityJPA map(@NonNull TaskGroupEntityDTO input, @NonNull @Context ReferenceCycleTracking context);

//	/** annotating parameter {@code out} with {@link MappingTarget} is essential for this method being called */
//	@BeforeMapping default void beforeMapping(
//			@NonNull                TaskGroupEntityDTO     in,
//			@NonNull @MappingTarget TaskGroupEntityJPA     out,
//			@NonNull @Context       ReferenceCycleTracking context)
//	{
//		out.beforeMapping(out, in, context);
//	}
//
//	/** annotating parameter {@code out} with {@link MappingTarget} is essential for this method being called */
//	@AfterMapping default void afterMapping(
//			@NonNull                TaskGroupEntityDTO     in,
//			@NonNull @MappingTarget TaskGroupEntityJPA     out,
//			@NonNull @Context       ReferenceCycleTracking context)
//	{
//		out.afterMapping(out, in, context);
//	}

	@Override default @NonNull Class<TaskGroupEntityJPA> outType() { return TaskGroupEntityJPA.class; }

	@Override default @NonNull TaskGroupEntityJPA create(TaskGroupEntityDTO in) { return new TaskGroupEntityJPA(in.name()); }

	@ObjectFactory
	@Override default @NonNull TaskGroupEntityJPA lookupOrCreate(
			@NonNull TaskGroupEntityDTO taskGroupEntityDTO, @NonNull ReferenceCycleTracking context)
	{
		return MappableCyclicMapper.super.lookupOrCreate(taskGroupEntityDTO, context);
	}

	//	/** object factory will be called by mapstruct */
//	@ObjectFactory @NonNull default TaskGroupEntityJPA lookupOrCreate(
//			@NonNull          TaskGroupEntityDTO     input,
//			@NonNull @Context ReferenceCycleTracking context)
//	{
//		TaskGroupEntityJPA result = context.get(input, TaskGroupEntityJPA.class);
//		if (result == null)
//		{
//			result = new TaskGroupEntityJPA(input.name());
//			context.put(input, result);
//		}
//		return result;
//	}
}