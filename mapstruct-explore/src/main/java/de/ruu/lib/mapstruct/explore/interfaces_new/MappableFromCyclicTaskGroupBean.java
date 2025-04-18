package de.ruu.lib.mapstruct.explore.interfaces_new;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import lombok.NonNull;

public interface MappableFromCyclicTaskGroupBean// extends MappableFromCyclic<TaskGroupBean>
{
	void beforeMapping(@NonNull TaskGroupBean in, @NonNull ReferenceCycleTracking context);
	void  afterMapping(@NonNull TaskGroupBean in, @NonNull ReferenceCycleTracking context);
}