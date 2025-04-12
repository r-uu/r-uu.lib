package de.ruu.lib.mapstruct.explore.interfaces;

import de.ruu.lib.mapstruct.MappableCyclicMapper;
import de.ruu.lib.mapstruct.explore.common.TaskGroupEntityDTO;
import de.ruu.lib.mapstruct.explore.common.TaskGroupEntityJPA;
import lombok.NonNull;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;
import org.mapstruct.factory.Mappers;

/** {@link TaskGroupEntityDTO} -> {@link TaskGroupEntityJPA} */
@Mapper
interface Map_TaskGroup_EntityDTO_EntityJPA extends MappableCyclicMapper<TaskGroupEntityDTO, TaskGroupEntityJPA>
{
	Map_TaskGroup_EntityDTO_EntityJPA INSTANCE = Mappers.getMapper(Map_TaskGroup_EntityDTO_EntityJPA.class);

	@Override default @NonNull Class<TaskGroupEntityJPA> outType() { return TaskGroupEntityJPA.class; }

	@Override default @NonNull TaskGroupEntityJPA create(TaskGroupEntityDTO in)
	{
		return new TaskGroupEntityJPA(in.name());
	}
}