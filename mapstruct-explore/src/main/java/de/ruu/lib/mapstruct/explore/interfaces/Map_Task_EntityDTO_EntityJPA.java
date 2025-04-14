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
@Mapper
public interface Map_Task_EntityDTO_EntityJPA extends MappableCyclicMapper<TaskEntityDTO, TaskEntityJPA>
{
	Map_Task_EntityDTO_EntityJPA INSTANCE = Mappers.getMapper(Map_Task_EntityDTO_EntityJPA.class);

	@Override default @NonNull Class<TaskEntityJPA> outType() { return TaskEntityJPA.class; }

	@Override default @NonNull TaskEntityJPA create(@NonNull TaskEntityDTO in, @NonNull ReferenceCycleTracking context)
	{
		return new TaskEntityJPA(in.taskGroup().toEntity(context), in.name());
	}
}