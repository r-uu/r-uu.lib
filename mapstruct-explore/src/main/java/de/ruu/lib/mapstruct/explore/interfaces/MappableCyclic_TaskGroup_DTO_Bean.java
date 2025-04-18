package de.ruu.lib.mapstruct.explore.interfaces;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import de.ruu.lib.mapstruct.explore.common.TaskGroupEntityDTO;
import lombok.NonNull;

public interface MappableCyclic_TaskGroup_DTO_Bean
{
	void beforeMapping(@NonNull TaskGroupEntityDTO in, @NonNull ReferenceCycleTracking context);
	void  afterMapping(@NonNull TaskGroupEntityDTO in, @NonNull ReferenceCycleTracking context);
}