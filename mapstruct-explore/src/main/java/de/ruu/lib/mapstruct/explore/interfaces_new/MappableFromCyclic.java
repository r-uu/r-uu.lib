package de.ruu.lib.mapstruct.explore.interfaces_new;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import lombok.NonNull;

public interface MappableFromCyclic<IN>
{
	void beforeMapping(@NonNull IN in, ReferenceCycleTracking context);
	void  afterMapping(@NonNull IN in, ReferenceCycleTracking context);
}