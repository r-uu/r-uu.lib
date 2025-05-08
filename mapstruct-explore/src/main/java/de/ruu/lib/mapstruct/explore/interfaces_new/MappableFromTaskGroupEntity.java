package de.ruu.lib.mapstruct.explore.interfaces_new;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import lombok.NonNull;

public interface MappableFromTaskGroupEntity extends MappableBidirectionalFrom<TaskGroupEntity>
{
	void beforeMapping(@NonNull TaskGroupEntity in, @NonNull ReferenceCycleTracking context);
	void  afterMapping(@NonNull TaskGroupEntity in, @NonNull ReferenceCycleTracking context);
}