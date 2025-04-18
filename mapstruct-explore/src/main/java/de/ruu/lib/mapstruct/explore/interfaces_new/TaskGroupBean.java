package de.ruu.lib.mapstruct.explore.interfaces_new;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import lombok.NonNull;

public class TaskGroupBean
		implements
				MappableFromCyclicTaskGroupDTO, MappableFromCyclicTaskGroupFXBean
{
	@Override public void beforeMapping(@NonNull TaskGroupDTO    in, @NonNull ReferenceCycleTracking context) { }
	@Override public void  afterMapping(@NonNull TaskGroupDTO    in, @NonNull ReferenceCycleTracking context) { }
	@Override public void beforeMapping(@NonNull TaskGroupFXBean in, @NonNull ReferenceCycleTracking context) { }
	@Override public void  afterMapping(@NonNull TaskGroupFXBean in, @NonNull ReferenceCycleTracking context) { }
}