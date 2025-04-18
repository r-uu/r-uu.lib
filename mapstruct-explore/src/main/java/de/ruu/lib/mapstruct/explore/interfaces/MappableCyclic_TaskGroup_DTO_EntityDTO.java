package de.ruu.lib.mapstruct.explore.interfaces;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import de.ruu.lib.mapstruct.explore.common.TaskGroupDTO;
import lombok.NonNull;

public interface MappableCyclic_TaskGroup_DTO_EntityDTO
{
	void beforeMapping(@NonNull TaskGroupDTO in, @NonNull ReferenceCycleTracking context);
	void  afterMapping(@NonNull TaskGroupDTO in, @NonNull ReferenceCycleTracking context);
}