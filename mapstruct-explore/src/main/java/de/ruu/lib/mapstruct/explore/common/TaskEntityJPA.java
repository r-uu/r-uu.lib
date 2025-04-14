package de.ruu.lib.mapstruct.explore.common;

import de.ruu.lib.jpa.core.Entity;
import de.ruu.lib.mapstruct.MappableCyclic;
import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import de.ruu.lib.mapstruct.explore.interfaces.Map_TaskGroup_EntityJPA_EntityDTO;
import de.ruu.lib.mapstruct.explore.interfaces.Map_Task_EntityJPA_EntityDTO;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
public class TaskEntityJPA
		implements
				Task<TaskGroupEntityJPA, TaskEntityJPA>,
				Entity<Long>,
				MappableCyclic<TaskEntityDTO, TaskEntityJPA>
{
	private TaskGroupEntityJPA taskGroup;
	private String             name;

	@Override public @NonNull TaskGroupEntityJPA taskGroup() { return taskGroup; }

	@Override public @NonNull String     name       () { return ""; }
	@Override public @NonNull Boolean    closed     () { return false; }
	@Override public Optional<String>    description() { return Optional.empty();}
	@Override public Optional<LocalDate> start      () { return Optional.empty(); }
	@Override public Optional<LocalDate> end        () { return Optional.empty(); }

	@Override public @NonNull TaskEntityJPA description(String description ) { return this; }
	@Override public @NonNull TaskEntityJPA start(LocalDate startEstimated ) { return this; }
	@Override public @NonNull TaskEntityJPA end  (LocalDate finishEstimated) { return this; }
	@Override public @NonNull TaskEntityJPA closed(Boolean closed          ) { return this; }

	@Override public Optional<TaskEntityJPA> superTask() { return Optional.empty(); }
	@Override public @NonNull TaskEntityJPA  superTask(TaskEntityJPA task) { return this; }

	@Override public Optional<Set<TaskEntityJPA>> subTasks() { return Optional.empty(); }
	@Override public Optional<Set<TaskEntityJPA>> predecessors() { return Optional.empty(); }

	@Override public Optional<Set<TaskEntityJPA>> successors() { return Optional.empty(); }
	@Override public boolean addSubTask(@NonNull TaskEntityJPA task) { return false; }

	@Override public boolean removeSubTask(@NonNull TaskEntityJPA task) { return false; }
	@Override public boolean addPredecessor(@NonNull TaskEntityJPA task) { return false; }

	@Override public boolean removePredecessor(@NonNull TaskEntityJPA task) { return false; }
	@Override public boolean addSuccessor(@NonNull TaskEntityJPA task) { return false; }

	@Override public boolean removeSuccessor(@NonNull TaskEntityJPA task) { return false; }

	@Override public @Nullable Long  id     () { return 0L; }
	@Override public @Nullable Short version() { return 0;  }

	@Override public void beforeMapping(@NonNull TaskEntityDTO in, @NonNull ReferenceCycleTracking context) { }
	@Override public void  afterMapping(@NonNull TaskEntityDTO in, @NonNull ReferenceCycleTracking context) { }

	public TaskEntityDTO toDTO(@NonNull ReferenceCycleTracking context)
	{
		return Map_Task_EntityJPA_EntityDTO.INSTANCE.map(this, context);
	}
}