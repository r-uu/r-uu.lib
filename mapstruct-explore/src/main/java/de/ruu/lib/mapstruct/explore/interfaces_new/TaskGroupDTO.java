package de.ruu.lib.mapstruct.explore.interfaces_new;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import lombok.NonNull;

public class TaskGroupDTO
		implements MappableFromCyclicTaskGroupEntityDTO, MappableFromCyclicTaskGroupBean
{
	@Override
	public void beforeMapping(@NonNull TaskGroupEntityDTO in, @NonNull ReferenceCycleTracking context) {
	}

	@Override
	public void afterMapping(@NonNull TaskGroupEntityDTO in, @NonNull ReferenceCycleTracking context) {
	}

	@Override
	public void beforeMapping(@NonNull TaskGroupBean in, @NonNull ReferenceCycleTracking context) {
	}

	@Override
	public void afterMapping(@NonNull TaskGroupBean in, @NonNull ReferenceCycleTracking context) {
	}
}