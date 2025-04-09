package de.ruu.lib.mapstruct.explore.jeeeraaah.common.jpadto;

import de.ruu.lib.jpa.core.Entity;
import de.ruu.lib.mapstruct.explore.jeeeraaah.common.Task;
import lombok.NonNull;

import java.util.Optional;
import java.util.Set;

public interface TaskEntity<TG extends TaskGroupEntity>
		extends Task<TG>, Entity<Long>
{
	@Override
	Optional<TaskEntity> superTask();
//	@Override
//	Optional<TaskEntity>           superTask();
//	@Override
//	@NonNull TaskEntity            superTask(TaskEntity superTask);

	@Override
	<T extends de.ruu.lib.mapstruct.explore.jeeeraaah.common.Task<TaskGroupEntity>> @NonNull T superTask(T superTask);
	//	@Override Optional<Set<TaskEntity>>   subTasks();

	@Override
	Optional<Set<? extends de.ruu.lib.mapstruct.explore.jeeeraaah.common.Task<TaskGroupEntity>>> subTasks();

	@Override Optional<Set<TaskEntity>> predecessors();
	@Override Optional<Set<TaskEntity>> successors();

	@Override boolean    addSubTask    (@NonNull TaskEntity entity);
	@Override boolean removeSubTask    (@NonNull de.ruu.lib.mapstruct.explore.jeeeraaah.common.Task entity);

	@Override boolean    addPredecessor(@NonNull de.ruu.lib.mapstruct.explore.jeeeraaah.common.Task entity);
	@Override boolean removePredecessor(@NonNull de.ruu.lib.mapstruct.explore.jeeeraaah.common.Task entity);

	@Override boolean    addSuccessor  (@NonNull de.ruu.lib.mapstruct.explore.jeeeraaah.common.Task entity);
	@Override boolean removeSuccessor  (@NonNull de.ruu.lib.mapstruct.explore.jeeeraaah.common.Task entity);

	interface TaskGroup<T2 extends Task<?>>
	{
		Optional<Set<T2>> tasks();
	}
	interface Task<TG2 extends TaskGroup<?>>
	{
		TG2 taskGroup();
		Optional<? extends Task<TG2>> superTask(TG2 group);
	}
	class TaskGroupImpl implements TaskGroup<TaskImpl>
	{
		@Override public Optional<Set<TaskImpl>> tasks()
				{ return Optional.empty(); }
	}
	class TaskImpl implements Task<TaskGroupImpl>
	{
		@Override public TaskGroupImpl taskGroup()
				{ return null; }
		@Override public Optional<TaskImpl> superTask(TaskGroupImpl group)
				{ return Optional.empty(); }
	}
}