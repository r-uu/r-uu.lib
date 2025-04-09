package de.ruu.lib.mapstruct.explore.jeeeraaah.common.jpadto;

import de.ruu.lib.mapstruct.explore.jeeeraaah.common.Task;
import lombok.NonNull;

import java.util.Optional;
import java.util.Set;

@Deprecated
interface TaskEntity extends Task<TaskGroupEntity, TaskEntity>
{
	@Override Optional<TaskEntity> superTask();
	@Override @NonNull TaskEntity  superTask(TaskEntity task);

	@Override Optional<Set<TaskEntity>> subTasks();

	@Override Optional<Set<TaskEntity>> predecessors();
	@Override Optional<Set<TaskEntity>> successors();

	@Override boolean    addSubTask    (@NonNull TaskEntity task);
	@Override boolean removeSubTask    (@NonNull TaskEntity task);

	@Override boolean    addPredecessor(@NonNull TaskEntity task);
	@Override boolean removePredecessor(@NonNull TaskEntity task);

	@Override boolean    addSuccessor  (@NonNull TaskEntity task);
	@Override boolean removeSuccessor  (@NonNull TaskEntity task);
}