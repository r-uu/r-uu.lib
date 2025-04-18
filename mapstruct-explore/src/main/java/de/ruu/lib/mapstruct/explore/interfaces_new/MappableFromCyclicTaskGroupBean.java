package de.ruu.lib.mapstruct.explore.interfaces_new;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import lombok.NonNull;

public interface MappableFromCyclicTaskGroupBean extends MappableFromCyclic<TaskGroupBean>
{
	@Override void beforeMapping(@NonNull TaskGroupBean in, @NonNull ReferenceCycleTracking context);
	@Override void  afterMapping(@NonNull TaskGroupBean in, @NonNull ReferenceCycleTracking context);
}