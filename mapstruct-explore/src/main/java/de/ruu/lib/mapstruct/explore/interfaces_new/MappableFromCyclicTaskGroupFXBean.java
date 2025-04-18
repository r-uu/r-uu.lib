package de.ruu.lib.mapstruct.explore.interfaces_new;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import lombok.NonNull;

public interface MappableFromCyclicTaskGroupFXBean extends MappableFromCyclic<TaskGroupFXBean>
{
	@Override void beforeMapping(@NonNull TaskGroupFXBean in, @NonNull ReferenceCycleTracking context);
	@Override void  afterMapping(@NonNull TaskGroupFXBean in, @NonNull ReferenceCycleTracking context);
}