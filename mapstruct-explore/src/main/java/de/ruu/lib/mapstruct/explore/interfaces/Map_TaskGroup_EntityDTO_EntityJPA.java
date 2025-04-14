package de.ruu.lib.mapstruct.explore.interfaces;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import de.ruu.lib.mapstruct.explore.common.TaskGroupEntityDTO;
import de.ruu.lib.mapstruct.explore.common.TaskGroupEntityJPA;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/** {@link TaskGroupEntityDTO} -> {@link TaskGroupEntityJPA} */
@Mapper public interface Map_TaskGroup_EntityDTO_EntityJPA
		extends MappableCyclicMapper<TaskGroupEntityDTO, TaskGroupEntityJPA>
{
	Map_TaskGroup_EntityDTO_EntityJPA INSTANCE = Mappers.getMapper(Map_TaskGroup_EntityDTO_EntityJPA.class);

	@Override default @NonNull Class<TaskGroupEntityJPA> outType() { return TaskGroupEntityJPA.class; }

	@Override default @NonNull TaskGroupEntityJPA create(@NonNull TaskGroupEntityDTO in, @NonNull ReferenceCycleTracking context)
	{
		return new TaskGroupEntityJPA(in.name());
	}
}