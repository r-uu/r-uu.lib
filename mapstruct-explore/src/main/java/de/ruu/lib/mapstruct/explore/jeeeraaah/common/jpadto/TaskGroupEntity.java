package de.ruu.lib.mapstruct.explore.jeeeraaah.common.jpadto;

import de.ruu.lib.jpa.core.Entity;
import de.ruu.lib.mapstruct.explore.jeeeraaah.common.Task;
import de.ruu.lib.mapstruct.explore.jeeeraaah.common.TaskGroup;

import java.util.Optional;
import java.util.Set;

public interface TaskGroupEntity extends TaskGroup<TaskEntity>, Entity<Long>
{
	@Override Optional<Set<TaskEntity>>       tasks();
	@Override boolean                   removeTask (TaskEntity task);
}