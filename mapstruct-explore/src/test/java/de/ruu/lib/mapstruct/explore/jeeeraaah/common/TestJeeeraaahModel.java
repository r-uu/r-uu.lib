package de.ruu.lib.mapstruct.explore.jeeeraaah.common;

import java.util.Optional;
import java.util.Set;

public class TestJeeeraaahModel
{
	interface TaskGroup<T extends Task<?, ?>>
	{
		Optional<Set<T>> tasks();
	}
	interface Task<TG extends TaskGroup<? extends Task<TG, ?>>, SELF extends Task<TG, SELF>>
	{
		TG taskGroup();
		Optional<SELF> superTask(SELF group);
	}
	class TaskGroupImpl implements TaskGroup<TaskImpl>
	{
		@Override public Optional<Set<TaskImpl>> tasks() { return Optional.empty(); }
	}
	class TaskImpl implements Task<TaskGroupImpl, TaskImpl>
	{
		@Override public TaskGroupImpl taskGroup() { return null; }
		@Override public Optional<TaskImpl> superTask(TaskImpl group) { return Optional.empty(); }
	}
}