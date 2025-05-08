package de.ruu.lib.mapstruct.explore.interfaces_new;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import lombok.NonNull;

public class TaskGroupFXBean
		implements MappableFromTaskGroupBean
{
	@Override public void beforeMapping(@NonNull TaskGroupBean in, @NonNull ReferenceCycleTracking context) { }
	@Override public void  afterMapping(@NonNull TaskGroupBean in, @NonNull ReferenceCycleTracking context) { }
}