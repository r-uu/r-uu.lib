package de.ruu.lib.mapstruct;

import lombok.NonNull;

public interface MappableCyclic<IN extends MappableCyclic<IN, OUT>, OUT extends MappableCyclic<OUT, IN>>
{
	void beforeMapping(@NonNull IN in, @NonNull OUT out, @NonNull ReferenceCycleTracking context);
	void  afterMapping(@NonNull IN in, @NonNull OUT out, @NonNull ReferenceCycleTracking context);
}