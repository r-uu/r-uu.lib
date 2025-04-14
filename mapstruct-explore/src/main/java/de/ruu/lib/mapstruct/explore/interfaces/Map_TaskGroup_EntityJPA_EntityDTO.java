package de.ruu.lib.mapstruct.explore.interfaces;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import de.ruu.lib.mapstruct.explore.common.TaskGroupEntityDTO;
import de.ruu.lib.mapstruct.explore.common.TaskGroupEntityJPA;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/** {@link TaskGroupEntityDTO} -> {@link TaskGroupEntityJPA} */
@Mapper public interface Map_TaskGroup_EntityJPA_EntityDTO
		extends MappableCyclicMapper<TaskGroupEntityJPA, TaskGroupEntityDTO>
{
	Map_TaskGroup_EntityJPA_EntityDTO INSTANCE = Mappers.getMapper(Map_TaskGroup_EntityJPA_EntityDTO.class);

	@Override default @NonNull Class<TaskGroupEntityDTO> outType() { return TaskGroupEntityDTO.class; }

	@Override default @NonNull TaskGroupEntityDTO create(@NonNull TaskGroupEntityJPA in, @NonNull ReferenceCycleTracking context)
	{
		return new TaskGroupEntityDTO(in.name());
	}
}