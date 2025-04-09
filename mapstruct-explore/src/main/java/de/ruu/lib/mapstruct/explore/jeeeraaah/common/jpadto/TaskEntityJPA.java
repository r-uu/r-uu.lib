package de.ruu.lib.mapstruct.explore.jeeeraaah.common.jpadto;

import de.ruu.lib.mapstruct.explore.jeeeraaah.common.Task;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

class TaskEntityJPA implements Task<TaskGroupEntityJPA, TaskEntityJPA>
{
	private TaskGroupEntityJPA taskGroup;

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
	@Override public @NonNull TaskEntityJPA superTask(TaskEntityJPA task) { return this; }

	@Override public Optional<Set<TaskEntityJPA>> subTasks() { return Optional.empty(); }
	@Override public Optional<Set<TaskEntityJPA>> predecessors() { return Optional.empty(); }

	@Override public Optional<Set<TaskEntityJPA>> successors() { return Optional.empty(); }
	@Override public boolean addSubTask(@NonNull TaskEntityJPA task) { return false; }

	@Override public boolean removeSubTask(@NonNull TaskEntityJPA task) { return false; }
	@Override public boolean addPredecessor(@NonNull TaskEntityJPA task) { return false; }

	@Override public boolean removePredecessor(@NonNull TaskEntityJPA task) { return false; }
	@Override public boolean addSuccessor(@NonNull TaskEntityJPA task) { return false; }

	@Override public boolean removeSuccessor(@NonNull TaskEntityJPA task) { return false; }
}
