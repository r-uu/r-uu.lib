package de.ruu.lib.mapstruct.explore.interfaces_new;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import lombok.NonNull;

public interface MappableFromTaskGroupDTO extends MappableBidirectionalFrom<TaskGroupDTO>
{
	void beforeMapping(@NonNull TaskGroupDTO in, @NonNull ReferenceCycleTracking context);
	void  afterMapping(@NonNull TaskGroupDTO in, @NonNull ReferenceCycleTracking context);
}