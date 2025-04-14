package de.ruu.lib.mapstruct.explore.interfaces;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import de.ruu.lib.mapstruct.explore.common.TaskEntityDTO;
import de.ruu.lib.mapstruct.explore.common.TaskEntityJPA;
import de.ruu.lib.mapstruct.explore.common.TaskGroupEntityDTO;
import de.ruu.lib.mapstruct.explore.common.TaskGroupEntityJPA;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/** {@link TaskGroupEntityDTO} -> {@link TaskGroupEntityJPA} */
@Mapper public interface Map_Task_EntityJPA_EntityDTO extends MappableCyclicMapper<TaskEntityJPA, TaskEntityDTO>
{
	Map_Task_EntityJPA_EntityDTO INSTANCE = Mappers.getMapper(Map_Task_EntityJPA_EntityDTO.class);

	@Override default @NonNull Class<TaskEntityDTO> outType() { return TaskEntityDTO.class; }

	@Override default @NonNull TaskEntityDTO create(@NonNull TaskEntityJPA in, @NonNull ReferenceCycleTracking context)
	{
		return new TaskEntityDTO(in.taskGroup().toDTO(context), in.name());
	}
}