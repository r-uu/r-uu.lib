package de.ruu.lib.mapstruct.explore.jeeeraaah.common.jpadto;

import de.ruu.lib.mapstruct.explore.jeeeraaah.common.Task;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

class TaskEntityDTO implements Task<TaskGroupEntityDTO, TaskEntityDTO>
{
	private TaskGroupEntityDTO taskGroup;

	@Override public @NonNull TaskGroupEntityDTO taskGroup() { return taskGroup; }

	@Override public @NonNull String     name       () { return ""; }
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
}
