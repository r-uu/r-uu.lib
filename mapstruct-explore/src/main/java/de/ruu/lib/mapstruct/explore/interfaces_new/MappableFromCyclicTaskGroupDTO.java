package de.ruu.lib.mapstruct.explore.interfaces_new;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import lombok.NonNull;

public interface MappableFromCyclicTaskGroupDTO extends MappableFromCyclic<TaskGroupDTO>
{
	@Override void beforeMapping(@NonNull TaskGroupDTO in, @NonNull ReferenceCycleTracking context);
	@Override void  afterMapping(@NonNull TaskGroupDTO in, @NonNull ReferenceCycleTracking context);
}