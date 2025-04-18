package de.ruu.lib.mapstruct.explore.interfaces;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import de.ruu.lib.mapstruct.explore.common.TaskGroupBean;
import de.ruu.lib.mapstruct.explore.common.TaskGroupEntityDTO;
import lombok.NonNull;

public interface MappableCyclic_TaskGroup_Bean_DTO
{
	void beforeMapping(@NonNull TaskGroupBean in, @NonNull ReferenceCycleTracking context);
	void  afterMapping(@NonNull TaskGroupBean in, @NonNull ReferenceCycleTracking context);
}