package de.ruu.lib.mapstruct.explore.common;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import de.ruu.lib.mapstruct.explore.interfaces.MappableCyclic_TaskGroup_Bean_DTO;
import de.ruu.lib.mapstruct.explore.interfaces.MappableCyclic_TaskGroup_EntityDTO_DTO;
import lombok.NonNull;

public class TaskGroupDTO extends TaskGroupEntityDTO
		implements
				MappableCyclic_TaskGroup_EntityDTO_DTO,
				MappableCyclic_TaskGroup_Bean_DTO
{
	public TaskGroupDTO(@NonNull String name) { super(name); }

	@Override public void beforeMapping(@NonNull TaskGroupEntityDTO in, @NonNull ReferenceCycleTracking context) { }
	@Override public void  afterMapping(@NonNull TaskGroupEntityDTO in, @NonNull ReferenceCycleTracking context) { }

	@Override public void beforeMapping(@NonNull TaskGroupBean in, @NonNull ReferenceCycleTracking context) { }
	@Override public void  afterMapping(@NonNull TaskGroupBean in, @NonNull ReferenceCycleTracking context) { }
}