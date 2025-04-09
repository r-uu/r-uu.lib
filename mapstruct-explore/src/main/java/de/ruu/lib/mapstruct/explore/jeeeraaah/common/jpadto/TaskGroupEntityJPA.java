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
class TaskGroupEntityJPA
		implements
				TaskGroup<TaskEntityJPA>,
				Entity<Long>,
				MappableCyclic<TaskGroupEntityJPA, TaskGroupEntityDTO>
{
	private @NonNull String name;

	@Override public @NonNull String name() { return name; }
	@Override public @NonNull TaskGroupEntityJPA name(@NonNull String name)
	{
		this.name = name;
		return this;
	}

	@Override public Optional<String> description() { return Optional.empty(); }
	@Override public @NonNull TaskGroupEntityJPA description(@Nullable String description) { return this; }

	@Override public Optional<Set<TaskEntityJPA>> tasks() { return Optional.empty(); }
	@Override public boolean removeTask(TaskEntityJPA task) { return false; }

	@Override public @Nullable Long  id() { return 0L; }
	@Override public @Nullable Short version() { return 0; }

	@Override public void beforeMapping(
			@NonNull TaskGroupEntityJPA taskGroupEntityJPA,
			@NonNull TaskGroupEntityDTO taskGroupEntityDTO,
			@NonNull ReferenceCycleTracking context)
	{
	}

	@Override public void afterMapping(
			@NonNull TaskGroupEntityJPA taskGroupEntityJPA,
			@NonNull TaskGroupEntityDTO taskGroupEntityDTO,
			@NonNull ReferenceCycleTracking context)
	{
	}
}