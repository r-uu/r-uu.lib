package de.ruu.lib.mapstruct.explore.jeeeraaah.common.jpadto;

import de.ruu.lib.jpa.core.Entity;
import de.ruu.lib.mapstruct.explore.jeeeraaah.common.Task;

@Deprecated
public interface TaskDTO extends Task<TaskGroupDTO, TaskDTO>, Entity<Long> { }