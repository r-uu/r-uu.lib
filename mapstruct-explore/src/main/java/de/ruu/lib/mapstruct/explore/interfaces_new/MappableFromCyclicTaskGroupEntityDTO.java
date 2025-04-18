package de.ruu.lib.mapstruct.explore.interfaces_new;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import lombok.NonNull;

public interface MappableFromCyclicTaskGroupEntityDTO// extends MappableFromCyclic<TaskGroupEntityDTO>
{
	void beforeMapping(@NonNull TaskGroupEntityDTO in, @NonNull ReferenceCycleTracking context);
	void  afterMapping(@NonNull TaskGroupEntityDTO in, @NonNull ReferenceCycleTracking context);
}