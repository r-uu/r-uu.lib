package de.ruu.lib.mapstruct.explore.abstract_classes;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import de.ruu.lib.mapstruct.explore.common.TaskGroupEntityDTO;
import de.ruu.lib.mapstruct.explore.common.TaskGroupEntityJPA;
import lombok.NonNull;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;
import org.mapstruct.factory.Mappers;

/** {@link TaskGroupEntityDTO} -> {@link TaskGroupEntityJPA} */
@Mapper abstract class Map_TaskGroup_EntityDTO_EntityJPA extends MappableCyclicMapper<TaskGroupEntityDTO, TaskGroupEntityJPA>
{
	Map_TaskGroup_EntityDTO_EntityJPA INSTANCE = Mappers.getMapper(Map_TaskGroup_EntityDTO_EntityJPA.class);

//	@NonNull TaskGroupEntityJPA map(@NonNull TaskGroupEntityDTO input, @NonNull @Context ReferenceCycleTracking context);

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

	@Override public @NonNull Class<TaskGroupEntityJPA> outType() { return TaskGroupEntityJPA.class; }

	@Override public @NonNull TaskGroupEntityJPA create(TaskGroupEntityDTO in)
	{
		return new TaskGroupEntityJPA(in.name());
	}

	@ObjectFactory
	@Override public @NonNull TaskGroupEntityJPA lookupOrCreate(
			@NonNull TaskGroupEntityDTO taskGroupEntityDTO, @NonNull @Context ReferenceCycleTracking context)
	{
		return super.lookupOrCreate(taskGroupEntityDTO, context);
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