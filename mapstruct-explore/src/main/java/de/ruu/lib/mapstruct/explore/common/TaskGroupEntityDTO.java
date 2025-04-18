package de.ruu.lib.mapstruct.explore.common;

import de.ruu.lib.jpa.core.Entity;
import de.ruu.lib.mapstruct.MappableCyclic;
import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import de.ruu.lib.mapstruct.explore.interfaces.Map_TaskGroup_EntityDTO_EntityJPA;
import de.ruu.lib.mapstruct.explore.interfaces.MappableCyclic_TaskGroup_DTO_EntityDTO;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
public class TaskGroupEntityDTO
		implements
				TaskGroup<TaskEntityDTO>,
				Entity<Long>,
				MappableCyclic<TaskGroupEntityJPA, TaskGroupEntityDTO>,
				MappableCyclic_TaskGroup_DTO_EntityDTO
{
	private @NonNull String name;

	@Override public @NonNull String name() { return name; }
	@Override public @NonNull TaskGroupEntityDTO name(@NonNull String name)
	{
		this.name = name;
		return this;
	}

	@Override public Optional<String> description() { return Optional.empty(); }
	@Override public @NonNull TaskGroupEntityDTO description(@Nullable String description) { return this; }

	@Override public Optional<Set<TaskEntityDTO>> tasks() { return Optional.empty(); }
	@Override public boolean removeTask(TaskEntityDTO task) { return false; }

	@Override public @Nullable Long  id() { return 0L; }
	@Override public @Nullable Short version() { return 0; }

	@Override public void beforeMapping(@NonNull TaskGroupEntityJPA in, @NonNull ReferenceCycleTracking context) { }
	@Override public void  afterMapping(@NonNull TaskGroupEntityJPA in, @NonNull ReferenceCycleTracking context) { }

	@Override public void beforeMapping(@NonNull TaskGroupDTO in, @NonNull ReferenceCycleTracking context) { }
	@Override public void  afterMapping(@NonNull TaskGroupDTO in, @NonNull ReferenceCycleTracking context) { }

	public TaskGroupEntityJPA toEntity(@NonNull ReferenceCycleTracking context)
	{
		return Map_TaskGroup_EntityDTO_EntityJPA.INSTANCE.map(this, context);
	}
}