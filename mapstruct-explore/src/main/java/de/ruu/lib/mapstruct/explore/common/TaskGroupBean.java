package de.ruu.lib.mapstruct.explore.common;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import de.ruu.lib.mapstruct.explore.interfaces.MappableCyclic_TaskGroup_DTO_Bean;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Set;

public class TaskGroupBean
		implements
				TaskGroup<TaskBean>,
				MappableCyclic_TaskGroup_DTO_Bean
{
	@Override public @NonNull String         name()        { return ""; }
	@Override public Optional<String>        description() { return Optional.empty(); }
	@Override public Optional<Set<TaskBean>> tasks()       { return Optional.empty(); }

	@Override public @NonNull TaskGroup<TaskBean> name       (@NonNull  String   name)        { return this; }
	@Override public @NonNull TaskGroup<TaskBean> description(@Nullable String   description) { return this; }
	@Override public boolean                      removeTask (          TaskBean task)        { return false; }

	@Override public void beforeMapping(@NonNull TaskGroupEntityDTO in, @NonNull ReferenceCycleTracking context) { }
	@Override public void  afterMapping(@NonNull TaskGroupEntityDTO in, @NonNull ReferenceCycleTracking context) { }
}