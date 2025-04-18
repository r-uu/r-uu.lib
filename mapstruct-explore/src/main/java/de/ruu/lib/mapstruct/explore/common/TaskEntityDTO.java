package de.ruu.lib.mapstruct.explore.common;

import de.ruu.lib.jpa.core.Entity;
import de.ruu.lib.mapstruct.MappableCyclic;
import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import de.ruu.lib.mapstruct.explore.interfaces.Map_Task_EntityDTO_EntityJPA;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
public class TaskEntityDTO
		implements
				Task<TaskGroupEntityDTO, TaskEntityDTO>,
				Entity<Long>,
				MappableCyclic<TaskEntityJPA, TaskEntityDTO>
{
	private TaskGroupEntityDTO taskGroup;
	private String             name;

	@Override public @NonNull TaskGroupEntityDTO taskGroup() { return taskGroup; }

	@Override public @NonNull String     name       () { return name; }
	@Override public @NonNull Boolean    closed     () { return false; }
	@Override public Optional<String>    description() { return Optional.empty();}
	@Override public Optional<LocalDate> start      () { return Optional.empty(); }
	@Override public Optional<LocalDate> end        () { return Optional.empty(); }

	@Override public @NonNull TaskEntityDTO description(String description ) { return this; }
	@Override public @NonNull TaskEntityDTO start(LocalDate startEstimated ) { return this; }
	@Override public @NonNull TaskEntityDTO end  (LocalDate finishEstimated) { return this; }
	@Override public @NonNull TaskEntityDTO closed(Boolean closed          ) { return this; }

	@Override public Optional<TaskEntityDTO> superTask() { return Optional.empty(); }
	@Override public @NonNull TaskEntityDTO superTask(TaskEntityDTO task) { return this; }

	@Override public Optional<Set<TaskEntityDTO>> subTasks() { return Optional.empty(); }
	@Override public Optional<Set<TaskEntityDTO>> predecessors() { return Optional.empty(); }

	@Override public Optional<Set<TaskEntityDTO>> successors() { return Optional.empty(); }
	@Override public boolean addSubTask(@NonNull TaskEntityDTO task) { return false; }

	@Override public boolean removeSubTask(@NonNull TaskEntityDTO task) { return false; }
	@Override public boolean addPredecessor(@NonNull TaskEntityDTO task) { return false; }

	@Override public boolean removePredecessor(@NonNull TaskEntityDTO task) { return false; }
	@Override public boolean addSuccessor(@NonNull TaskEntityDTO task) { return false; }

	@Override public boolean removeSuccessor(@NonNull TaskEntityDTO task) { return false; }

	@Override public @Nullable Long  id     () { return 0L; }
	@Override public @Nullable Short version() { return 0;  }

	@Override public void beforeMapping(@NonNull TaskEntityJPA in, @NonNull ReferenceCycleTracking context) { }
	@Override public void  afterMapping(@NonNull TaskEntityJPA in, @NonNull ReferenceCycleTracking context) { }

	public TaskEntityJPA toEntity(ReferenceCycleTracking context)
	{
		return Map_Task_EntityDTO_EntityJPA.INSTANCE.lookupOrCreate(this, context);
	}
}