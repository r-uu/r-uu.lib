package de.ruu.lib.mapstruct.explore.common;

import de.ruu.lib.jpa.core.Entity;
import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public class TaskBean
		implements
				Task<TaskGroupBean, TaskBean>,
				Entity<Long>
{
	private TaskGroupBean taskGroup;
	private String        name;

	@Override public @NonNull TaskGroupBean taskGroup() { return taskGroup; }

	@Override public @NonNull String     name       () { return name; }
	@Override public @NonNull Boolean    closed     () { return false; }
	@Override public Optional<String>    description() { return Optional.empty();}
	@Override public Optional<LocalDate> start      () { return Optional.empty(); }
	@Override public Optional<LocalDate> end        () { return Optional.empty(); }

	@Override public @NonNull TaskBean description(String description ) { return this; }
	@Override public @NonNull TaskBean start(LocalDate startEstimated ) { return this; }
	@Override public @NonNull TaskBean end  (LocalDate finishEstimated) { return this; }
	@Override public @NonNull TaskBean closed(Boolean closed          ) { return this; }

	@Override public Optional<TaskBean> superTask() { return Optional.empty(); }
	@Override public @NonNull TaskBean superTask(TaskBean task) { return this; }

	@Override public Optional<Set<TaskBean>> subTasks() { return Optional.empty(); }
	@Override public Optional<Set<TaskBean>> predecessors() { return Optional.empty(); }

	@Override public Optional<Set<TaskBean>> successors() { return Optional.empty(); }
	@Override public boolean addSubTask(@NonNull TaskBean task) { return false; }

	@Override public boolean removeSubTask(@NonNull TaskBean task) { return false; }
	@Override public boolean addPredecessor(@NonNull TaskBean task) { return false; }

	@Override public boolean removePredecessor(@NonNull TaskBean task) { return false; }
	@Override public boolean addSuccessor(@NonNull TaskBean task) { return false; }

	@Override public boolean removeSuccessor(@NonNull TaskBean task) { return false; }

	@Override public @Nullable Long  id     () { return 0L; }
	@Override public @Nullable Short version() { return 0;  }

	public void beforeMapping(@NonNull TaskEntityJPA in, @NonNull ReferenceCycleTracking context) { }
	public void  afterMapping(@NonNull TaskEntityJPA in, @NonNull ReferenceCycleTracking context) { }
}