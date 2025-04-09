package de.ruu.lib.mapstruct.explore.jeeeraaah.common.jpadto;

import de.ruu.lib.jpa.core.Entity;
import de.ruu.lib.mapstruct.MappableCyclic;
import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import de.ruu.lib.mapstruct.explore.jeeeraaah.common.TaskGroup;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
class TaskGroupEntityDTO
		implements
				TaskGroup<TaskEntityDTO>,
				Entity<Long>,
				MappableCyclic<TaskGroupEntityDTO, TaskGroupEntityJPA>
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

	@Override public void beforeMapping(
			@NonNull TaskGroupEntityDTO taskGroupEntityDTO,
			@NonNull TaskGroupEntityJPA taskGroupEntityJPA,
			@NonNull ReferenceCycleTracking context)
	{
	}

	@Override public void afterMapping(
			@NonNull TaskGroupEntityDTO taskGroupEntityDTO,
			@NonNull TaskGroupEntityJPA taskGroupEntityJPA,
			@NonNull ReferenceCycleTracking context)
	{
	}
}