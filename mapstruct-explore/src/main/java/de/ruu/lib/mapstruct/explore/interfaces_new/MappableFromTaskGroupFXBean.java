package de.ruu.lib.mapstruct.explore.interfaces_new;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import lombok.NonNull;

public interface MappableFromTaskGroupFXBean extends MappableBidirectionalFrom<TaskGroupFXBean>
{
	void beforeMapping(@NonNull TaskGroupFXBean in, @NonNull ReferenceCycleTracking context);
	void  afterMapping(@NonNull TaskGroupFXBean in, @NonNull ReferenceCycleTracking context);
}